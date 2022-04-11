package com.example.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.TaskDetailResponse

class TaskAdapter : ListAdapter<TaskDetailResponse, TaskAdapter.TaskViewHolder>(TaskDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskDetailResponse) {
            binding.task = task
            binding.executePendingBindings()
        }
    }
}

object TaskDiffCallback : DiffUtil.ItemCallback<TaskDetailResponse>() {
    override fun areItemsTheSame(oldItem: TaskDetailResponse, newItem: TaskDetailResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskDetailResponse, newItem: TaskDetailResponse): Boolean {
        return oldItem == newItem
    }

}