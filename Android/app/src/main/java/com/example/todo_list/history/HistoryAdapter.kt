package com.example.todo_list.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R
import com.example.todo_list.databinding.HistoryItemBinding
import com.example.todo_list.history.data.HistoryCard

class HistoryAdapter : ListAdapter<HistoryCard, HistoryAdapter.HistoryViewHolder>(diffUtil) {

    inner class HistoryViewHolder(private val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(card: HistoryCard) {
            binding.historyCard = card
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<HistoryItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.history_item,
            parent,
            false
        ).let { HistoryViewHolder(it) }


    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<HistoryCard>() {
    override fun areItemsTheSame(oldItem: HistoryCard, newItem: HistoryCard): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HistoryCard, newItem: HistoryCard): Boolean {
        return oldItem == newItem
    }

}