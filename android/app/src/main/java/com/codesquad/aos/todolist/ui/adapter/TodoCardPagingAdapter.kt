package com.codesquad.aos.todolist.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.databinding.ItemTodoCardBinding

class TodoCardPagingAdapter : PagingDataAdapter<Card, TodoCardPagingAdapter.CardViewHolder>(diffUtil) {

    class CardViewHolder(val binding: ItemTodoCardBinding ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card?) {
            binding.tvCardTitle?.text = card?.title
            binding.tvCardContent?.text = card?.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTodoCardBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_todo_card, parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(getItem(position))
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
}