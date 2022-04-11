package com.codesquad.aos.todolist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.databinding.ItemTodoCardBinding
import java.util.*

class TodoCardListAdapter(
    private val deleteTextClick: (deleteCardIndex: Int) -> Unit  // 메인 액티비티에서 전달하는 메서드
) : ListAdapter<Card, TodoCardListAdapter.CardViewHolder>(diffUtil) {

    inner class CardViewHolder(val binding: ItemTodoCardBinding ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card?, deleteTextClick: (deleteCard: Int) -> Unit) {
            binding.tvCardTitle?.text = card?.title
            binding.tvCardContent?.text = card?.content

            binding.tvRemove?.setOnClickListener {
                if(this.itemView.tag == true) {
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

    fun moveItem(from: Int, to:Int) {
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