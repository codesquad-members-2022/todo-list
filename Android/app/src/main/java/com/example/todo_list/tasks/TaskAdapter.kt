package com.example.todo_list.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R
import com.example.todo_list.databinding.TodoItemBinding
import com.example.todo_list.tasks.data.Task

class TodoAdapter : ListAdapter<Task, TodoAdapter.TodoViewHolder>(diffUtil),
    ItemTouchHelperListener {
    inner class TodoViewHolder(private val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.task = task
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingUtil.inflate<TodoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.todo_item,
            parent,
            false
        ).let {
            TodoViewHolder(it)
        }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        return true
    }

    override fun onItemSwipe(position: Int) {
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }

}