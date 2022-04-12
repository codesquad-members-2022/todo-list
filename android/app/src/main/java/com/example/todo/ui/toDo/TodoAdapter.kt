package com.example.todo.ui.toDo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TodoItemBinding
import com.example.todo.model.TodoItem

class TodoAdapter(
    todoDiffCallback: DiffUtil.ItemCallback<TodoItem>
) :
    ListAdapter<TodoItem, TodoAdapter.ViewHolder>(todoDiffCallback) {
    class ViewHolder(private val itemViewBinding: TodoItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(cardItem: TodoItem) {
            itemViewBinding.toDoItem = cardItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}