package com.example.todolist.ui.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Task
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.ui.TaskViewModel
import com.example.todolist.ui.common.DiffUtil

class TodoAdapter(
    val viewModel: TaskViewModel
) : ListAdapter<Task, TodoAdapter.TodoViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.task = task
            binding.executePendingBindings()
            deleteCard(task)
        }

        private fun deleteCard(task: Task) {
            binding.tvDeleteCard?.setOnClickListener {
                viewModel.deleteTodoCard(task)
            }
        }

    }

}