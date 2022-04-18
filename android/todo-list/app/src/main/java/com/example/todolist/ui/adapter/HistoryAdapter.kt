package com.example.todolist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.todolist.ui.common.HistoryDiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.History
import com.example.todolist.databinding.ItemLogBinding

class HistoryAdapter : ListAdapter<History, HistoryAdapter.HistoryViewHolder>(HistoryDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.HistoryViewHolder {
        val binding = ItemLogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HistoryViewHolder(private val binding: ItemLogBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(history: History) {
            binding.history = history
        }

    }

}