package com.example.todolist.ui.ongoing

import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.component1
import androidx.core.util.component2
import androidx.core.view.DragStartHelper
import androidx.draganddrop.DropHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Task
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.ui.TaskViewModel
import com.example.todolist.ui.common.DiffUtil

class OngoingAdapter(
    private val viewModel: TaskViewModel
) : ListAdapter<Task, OngoingAdapter.TodoViewHolder>(DiffUtil) {

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
            drag(task)
            dropHelper(task)
        }

        private fun deleteCard(task: Task) {
            binding.tvDeleteCard?.setOnClickListener {
                viewModel.deleteOngoingCard(task)
            }
        }

        private fun drag(task: Task) {
            DragStartHelper(itemView) { view, _ ->
                val task = task
                val intent = Intent().apply {
                    putExtra("task", task)
                }
                val dragClipData = ClipData.newIntent("draggedTask", intent)
                val dragShadowBuilder = View.DragShadowBuilder(view)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    view.startDragAndDrop(
                        dragClipData,
                        dragShadowBuilder,
                        null,
                        View.DRAG_FLAG_GLOBAL
                    )
                } else {
                    view.startDrag(dragClipData, dragShadowBuilder, view, 0)
                }
            }.attach()
        }

        private fun dropHelper(task: Task) {
            DropHelper.configureView(
                binding.root.context as Activity,
                itemView,
                arrayOf(ClipDescription.MIMETYPE_TEXT_INTENT),
            ) { _, payload ->
                val item = payload.clip.getItemAt(0)
                val (_, remaining) = payload.partition { it == item }

                when {
                    payload.clip.description.hasMimeType(ClipDescription.MIMETYPE_TEXT_INTENT) ->
                        handlePlainTextDrop(item, task)
                }
                remaining
            }
        }

        private fun handlePlainTextDrop(item: ClipData.Item, targetTask: Task) {
            val droppedTask = item.intent.getSerializableExtra("task") as Task
            when (droppedTask.status) {
                "todo" -> viewModel.deleteTodoCard(droppedTask)
                "ongoing" -> viewModel.deleteOngoingCard(droppedTask)
            }
            viewModel.dropOngoingCard(droppedTask, targetTask)

        }

    }

}