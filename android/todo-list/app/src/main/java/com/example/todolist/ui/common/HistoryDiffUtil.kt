package com.example.todolist.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.data.History

object HistoryDiffUtil : DiffUtil.ItemCallback<History>() {

    override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem == newItem
    }

}