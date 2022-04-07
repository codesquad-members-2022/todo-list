package com.example.todolist.ui.common

import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.data.Task

object DiffUtil : DiffUtil.ItemCallback<Task>() {

    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

}