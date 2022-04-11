package com.example.todolist.ui

import android.util.Log
import android.view.*
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.TaskDetailResponse

class TaskAdapter(private val viewModel: TaskViewModel) : ListAdapter<TaskDetailResponse, TaskAdapter.TaskViewHolder>(TaskDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

     inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {

        private lateinit var task: TaskDetailResponse

        fun bind(task: TaskDetailResponse) {
            this.task = task
            binding.task = task
            binding.executePendingBindings()
            itemView.setOnLongClickListener {
                showPopup(it)
                true
            }
        }

        private fun showPopup(view: View) {
            val wrapper = ContextThemeWrapper(view.context, R.style.PopupWindow)
            val popup = PopupMenu(wrapper, view)
            popup.menuInflater.inflate(R.menu.menu_popup, popup.menu)
            popup.setOnMenuItemClickListener(this)
            popup.show()
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.popup_go_done -> viewModel.moveDone(task)
                R.id.popup_modify -> Log.d("test",  "popup_modify")
                R.id.popup_delete -> viewModel.deleteTask(task)
            }
            return item != null
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