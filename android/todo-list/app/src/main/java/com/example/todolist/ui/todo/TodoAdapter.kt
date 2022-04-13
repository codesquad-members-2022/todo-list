package com.example.todolist.ui.todo

import android.app.Activity
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Canvas
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.util.component1
import androidx.core.util.component2
import androidx.core.view.DragStartHelper
import androidx.draganddrop.DropHelper
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.Task
import com.example.todolist.databinding.ItemTodoBinding
import com.example.todolist.ui.TaskViewModel
import com.example.todolist.ui.common.DiffUtil

class TodoAdapter(
    private val viewModel: TaskViewModel
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
            drag(task)
            dropHelper(task)
        }

        private fun deleteCard(task: Task) {
            binding.tvDeleteCard?.setOnClickListener {
                //추후 업데이트 예정
                when(task.status) {
                    "todo" -> viewModel.deleteTodoCard(task)
                    "ongoing" -> viewModel.deleteOngoingCard(task)
                    "complete" -> viewModel.deleteCompletedCard(task)
                }

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
                        handleCardDrop(item, task)
                }
                remaining
            }
        }

        private fun handleCardDrop(item: ClipData.Item, targetTask: Task) {
            val droppedTask = item.intent.getSerializableExtra("task") as Task
            when (droppedTask.status) {
                "todo" -> {
                    viewModel.deleteTodoCard(droppedTask)
                }
                "ongoing" -> {
                    viewModel.deleteOngoingCard(droppedTask)
                }
                "complete" -> {
                    viewModel.deleteCompletedCard(droppedTask)
                }
            }
            handleTargetCard(droppedTask, targetTask)
        }

        private fun handleTargetCard(droppedTask:Task, targetTask: Task) {
            when(targetTask.status) {
                "todo" -> {
                    viewModel.dropTodoCard(droppedTask, targetTask)
                }
                "ongoing" -> {
                    viewModel.dropOngoingCard(droppedTask, targetTask)
                }
                "complete" -> {
                    viewModel.dropCompletedCard(droppedTask, targetTask)
                }
            }
        }

        fun getDeleteTextViewWidth() =
            binding.tvDeleteCard?.width?.toFloat() ?: 0f
//            itemView.findViewById<View>(R.id.tv_delete_card).width.toFloat()


        fun getDeleteTextView() : TextView? {
            return binding.tvDeleteCard
        }
//            itemView.findViewById<View>(R.id.tv_delete_card)

        fun getSwipeView() =
            binding.cioScreen

        fun setTag(isClamped: Boolean) {
            itemView.tag = isClamped
        }

        fun getTag(): Boolean =
            itemView.tag as? Boolean ?: false

    }


}