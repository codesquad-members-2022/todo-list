package com.example.todolist.ui.adapter

import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.util.component1
import androidx.core.util.component2
import androidx.draganddrop.DropHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Card
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.ui.common.CardActionHandler
import com.example.todolist.ui.common.DiffUtil

class TodoAdapter(
    private val cardActionHandler: CardActionHandler
) : ListAdapter<Card, TodoAdapter.TodoViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.card = card
            binding.executePendingBindings()
            deleteCard(card)
            drag(card)
            dropHelper(card)
            popupMenu()
            binding.cioScreen.translationX = 0f
        }

        private fun deleteCard(card: Card) {
            binding.tvDeleteCard.setOnClickListener {
                card.cardId?.let { id -> cardActionHandler.deleteCard(id) }
            }
        }

        private fun drag(card: Card) {
            itemView.setOnTouchListener { view, event ->
                Log.d("TAG", "터치")
                onTouch(event, card)
                view.performClick()
                false
            }
        }

        private fun dropHelper(card: Card) {
            DropHelper.configureView(
                binding.root.context as Activity,
                itemView,
                arrayOf(ClipDescription.MIMETYPE_TEXT_INTENT),
            ) { _, payload ->
                val item = payload.clip.getItemAt(0)
                val (_, remaining) = payload.partition { it == item }

                when {
                    payload.clip.description.hasMimeType(ClipDescription.MIMETYPE_TEXT_INTENT) ->
                        handleCardDrop(item, card)
                }
                remaining
            }
        }

        fun getDeleteTextViewWidth() =
            binding.tvDeleteCard.width.toFloat()


        fun getDeleteTextView(): TextView {
            return binding.tvDeleteCard
        }

        fun getSwipeView() =
            binding.cioScreen

        fun getTag(): Boolean =
            itemView.tag as? Boolean ?: false

        private fun handleCardDrop(item: ClipData.Item, targetCard: Card) {
            val draggedCard = item.intent.getSerializableExtra("card") as Card
            cardActionHandler.dropCard(draggedCard, targetCard)
        }

        private fun onTouch(event: MotionEvent, card: Card) {
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    Log.d("TAG", "무브")
                    startDrag(card)
                }
            }
        }

        private fun popupMenu() {
            itemView.setOnLongClickListener {
                Log.d("TAG", "long click!")
                val popup = PopupMenu(binding.root.context, it)
                MenuInflater(binding.root.context).inflate(R.menu.menu_popup, popup.menu)

                popup.setOnMenuItemClickListener { menu ->
                    when (menu.itemId) {
                        R.id.action_move_to_completed -> Log.d("TAG", "변경")
                        R.id.action_modify -> Log.d("TAG", "수정")
                        R.id.action_delete -> Log.d("TAG", "삭제")
                    }
                    false
                }
                popup.show()
                true
            }
        }

        private fun startDrag(card: Card) {
            val intent = Intent().apply {
                putExtra("card", card)
            }
            val dragClipData = ClipData.newIntent("draggedCard", intent)
            val dragShadowBuilder = View.DragShadowBuilder(itemView)

            itemView.startDragAndDrop(
                dragClipData,
                dragShadowBuilder,
                null,
                View.DRAG_FLAG_GLOBAL
            )
        }

        fun setTag(isClamped: Boolean) {
            itemView.tag = isClamped
        }

    }

}