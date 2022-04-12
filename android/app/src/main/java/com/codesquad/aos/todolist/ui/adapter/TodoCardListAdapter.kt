package com.codesquad.aos.todolist.ui.adapter

import android.content.ClipData
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.databinding.ItemTodoCardBinding
import com.codesquad.aos.todolist.ui.DragListener
import java.util.*

class TodoCardListAdapter(
    private val deleteTextClick: (deleteCardIndex: Int) -> Unit  // 메인 액티비티에서 전달하는 메서드
) : ListAdapter<Card, TodoCardListAdapter.CardViewHolder>(diffUtil), View.OnTouchListener {

    inner class CardViewHolder(val binding: ItemTodoCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card?, deleteTextClick: (deleteCard: Int) -> Unit) {
            binding.tvCardTitle?.text = card?.title
            binding.tvCardContent?.text = card?.content

            binding.tvRemove?.setOnClickListener {
                if (this.itemView.tag == true) {
                    //removeItem(this.layoutPosition)
                    deleteTextClick.invoke(this.layoutPosition)  // 메인 액티비티에 구현된 메서드에 삭제할 카드의 인덱스 정보를 전달한다
                }
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

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position), deleteTextClick)
        holder.itemView.tag = position
        holder.itemView.alpha = 1f
        holder.itemView.setOnTouchListener(this)
        holder.itemView.setOnDragListener(DragListener())
        holder.itemView.findViewById<ConstraintLayout>(R.id.cvSwipeView).translationX = 0f
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Card>() {
            override fun areItemsTheSame(
                oldItem: Card,
                newItem: Card
            ): Boolean {  // id, 주민번호 등 특징적인 것으로 비교
                return oldItem.id == newItem.id
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
                val isDraggable = if (swipeViewTag != null) {
                    swipeViewTag as Boolean
                } else {
                    false
                }

                if (!isDraggable) {
                    val data = ClipData.newPlainText("", "")
                    view?.startDragAndDrop(data, shadowBuilder, view, 0)
                    return true
                }
                return false
            }
        }
        return false
    }
}