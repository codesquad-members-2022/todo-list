package com.codesquad.aos.todolist.ui.adapter

import android.content.ClipData
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.databinding.ItemTodoCardBinding
import com.codesquad.aos.todolist.ui.DataChangeListener
import com.codesquad.aos.todolist.ui.DragListener
import kotlinx.coroutines.*
import java.util.*

class TodoCardListAdapter(
    private val deleteTextClick: (deleteCardIndex: Int) -> Unit,  // 메인 액티비티에서 전달하는 메서드
    private val dataChangeListener: DataChangeListener,
    private val openEditDialog: (card: Card) -> Unit,
    private val moveItemToComplete: (cardId: Int) -> Unit
) : ListAdapter<Card, TodoCardListAdapter.CardViewHolder>(diffUtil), View.OnTouchListener {

    inner class CardViewHolder(val binding: ItemTodoCardBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnCreateContextMenuListener {

        init {
            itemView.setOnCreateContextMenuListener(this)  // Context 메뉴가 나타나도록 등록
        }

        fun bind(card: Card?, deleteTextClick: (deleteCard: Int) -> Unit) {
            binding.tvCardTitle?.text = card?.title
            binding.tvCardContent?.text = card?.content

            binding.tvRemove?.setOnClickListener {
                // 현재 tvRemove에 setOnClickListener가 걸려있어서 카드를 이동하는 경우 tvRemove 부분으로 드래그를 하면 안되고, 나머지 왼쪽 부분으로 하면 드래그가 이루어짐짐
                val viewTag =
                    this.itemView.findViewById<ConstraintLayout>(R.id.cvSwipeView).tag as? Boolean
                        ?: false
                if (viewTag) { // true면 삭제 영역 보임
                    //removeItem(this.layoutPosition)
                    deleteTextClick.invoke(card!!.cardId)  // 메인 액티비티에 구현된 메서드에 삭제할 카드의 인덱스 정보를 전달한다
                }
                /*  if (card!!.isSwiped) { // true면 삭제 영역 보임
                      //removeItem(this.layoutPosition)
                      deleteTextClick.invoke(this.layoutPosition)  // 메인 액티비티에 구현된 메서드에 삭제할 카드의 인덱스 정보를 전달한다
                  }*/
            }
        }

        override fun onCreateContextMenu(  // 메뉴의 형태 지정
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            val inflater: MenuInflater = MenuInflater(itemView.context)
            inflater.inflate(R.menu.card_items, menu)

            menu!!.getItem(0).setOnMenuItemClickListener {
                Log.d(
                    "AppTest",
                    "0 clicked, ${this.absoluteAdapterPosition}, cardId : ${getItem(this.absoluteAdapterPosition).cardId}"
                )  // 여기서 this = 뷰홀더!

                // 매인액티비티로 Card 객체 이동시키기

                true
            }
            menu!!.getItem(0).setOnMenuItemClickListener {
                Log.d("AppTest", "10 clicked")
                // 수정하기
                moveItemToComplete.invoke(getItem(this.absoluteAdapterPosition).cardId)
                true
            }
            menu!!.getItem(1).setOnMenuItemClickListener {
                Log.d("AppTest", "1 clicked")
                // 수정하기
                openEditDialog.invoke(getItem(this.absoluteAdapterPosition))
                true
            }
            menu!!.getItem(2).setOnMenuItemClickListener {
                Log.d("AppTest", "2 clicked")

                deleteTextClick.invoke(getItem(this.absoluteAdapterPosition).cardId)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTodoCardBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_todo_card, parent, false)
        return CardViewHolder(binding)
    }

    fun moveItem(from: Int, to: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, from, to)
        submitList(newList)
    }

    fun removeItem(removeItem: Int) {
        val newList = currentList.toMutableList()
        newList.removeAt(removeItem)
        submitList(newList)
    }

    val dragInstance: DragListener?
        get() = if (dataChangeListener != null) {
            DragListener(dataChangeListener)
        } else {
            Log.e(javaClass::class.simpleName, "Listener not initialized")
            null
        }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position), deleteTextClick)
        holder.itemView.tag = position  // 태그 값으로 현재 포지션이 들어가 있음,  리사이클러뷰 자체는 태그가 없고 각 아이템뷰들은 태그가 있다
        holder.itemView.alpha = 1f
        holder.itemView.setOnTouchListener(this)
        holder.itemView.setOnDragListener(DragListener(dataChangeListener))
        holder.itemView.findViewById<ConstraintLayout>(R.id.cvSwipeView).translationX = 0f
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(
                oldItem: Card,
                newItem: Card
            ): Boolean {  // id, 주민번호 등 특징적인 것으로 비교
                return oldItem.cardId == newItem.cardId
            }

            override fun areContentsTheSame(
                oldItem: Card,
                newItem: Card
            ): Boolean {
                return oldItem == newItem  // 내용들이 같은지 체크,  java의 equals !!
            }
            // areItemsTheSame 이 참이면 areContentsTheSame 가 호출된다
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        val shadowBuilder: View.DragShadowBuilder = View.DragShadowBuilder(view)
        val swipeViewTag = view?.findViewById<ConstraintLayout>(R.id.cvSwipeView)?.tag
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // DOWN에 넣으면 startDragAndDrop에서 드래그 이벤트를 가져가서 ACTION_MOVE 호출이 되지 않는다
                // + 메뉴도 나타나지 않음(아마 ContextMenu에서도 메뉴를 띄우기 위해 터치하는 순간 이벤트를 받아야 하는 것 같은데,
                // 그전에 startDragAndDrop이 이벤트를 가져가서 인식이 안되어 메뉴가 나타나지 않는 것 같음,  MOVE로 아래 코드를 옮기면 정상적으로 ContextMenu가 나타난다)
                Log.d("AppTest", "ACTION_DOWN")
                /*    val isDraggable = if (swipeViewTag != null) {
                        swipeViewTag as Boolean  // 삭제 영역이 활성화 되어있다면 swipeViewTag 값이 true, tag= any 타입 -> 여러 타입 할당 가능
                    } else {
                        false
                    }

                    if (!isDraggable) {
                        val data = ClipData.newPlainText("", "")
                        view?.startDragAndDrop(data, shadowBuilder, view, 0)
                        return true
                    }
                    return true*/
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("AppTest", "ACTION_MOVE")

                val isDraggable = if (swipeViewTag != null) {
                    swipeViewTag as Boolean  // 삭제 영역이 활성화 되어있다면 swipeViewTag 값이 true, tag= any 타입 -> 여러 타입 할당 가능
                } else {
                    false
                }

                if (!isDraggable) {
                    val data = ClipData.newPlainText("", "")
                    view?.startDragAndDrop(data, shadowBuilder, view, 0)
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                Log.d("AppTest", "ACTION_UP")

                return true
            }
        }
        return false
    }

    fun getIsSwiped(inx: Int): Boolean {
        return getItem(inx).isSwiped
    }

    fun setSwipe(inx: Int, isSwiped: Boolean) {
        getItem(inx).isSwiped = isSwiped
    }

    fun getFirstElementOrder(): Int {
        if (currentList.size == 0) {
            return -1
        }
        return getItem(currentList.size - 1).order
    }

}