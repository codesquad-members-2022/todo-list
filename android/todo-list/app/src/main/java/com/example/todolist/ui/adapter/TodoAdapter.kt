package com.example.todolist.ui.adapter

import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.util.component1
import androidx.core.util.component2
import androidx.core.view.DragStartHelper
import androidx.draganddrop.DropHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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
            binding.cioScreen.translationX= 0f
        }

        private fun deleteCard(card: Card) {
            binding.tvDeleteCard.setOnClickListener {
                card.cardId?.let { id -> cardActionHandler.deleteCard(id) }
            }
        }

        private fun drag(card: Card) {
            DragStartHelper(itemView) { view, _ ->

                val intent = Intent().apply {
                    putExtra("card", card)
                }
                val dragClipData = ClipData.newIntent("draggedCard", intent)
                val dragShadowBuilder = View.DragShadowBuilder(view)

                view.startDragAndDrop(
                    dragClipData,
                    dragShadowBuilder,
                    null,
                    View.DRAG_FLAG_GLOBAL
                )
            }.attach()
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

        private fun handleCardDrop(item: ClipData.Item, targetCard: Card) {
            val draggedCard = item.intent.getSerializableExtra("card") as Card
            cardActionHandler.dropCard(draggedCard, targetCard)
        }

        fun getDeleteTextViewWidth() =
            binding.tvDeleteCard.width.toFloat()


        fun getDeleteTextView() : TextView {
            return binding.tvDeleteCard
        }

        fun getSwipeView() =
            binding.cioScreen

        fun setTag(isClamped: Boolean) {
            itemView.tag = isClamped
        }

        fun getTag(): Boolean =
            itemView.tag as? Boolean ?: false

    }


}