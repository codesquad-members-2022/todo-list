package com.example.todolist.ui

import android.os.Build
import android.view.*
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.Status
import com.example.todolist.model.request.MoveTaskRequest
import com.example.todolist.model.response.TaskDetailResponse

class TaskAdapter(
    private val viewModel: TaskRemoteViewModel,
    private val listener: DialogListener,
) : ListAdapter<TaskDetailResponse, TaskAdapter.TaskViewHolder>(TaskDiffCallback),
    ItemTouchHelperListener {

    interface DialogListener {
        fun updateDialog(task: TaskDetailResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.tag = position
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener, View.OnTouchListener {

        private lateinit var task: TaskDetailResponse

        fun bind(task: TaskDetailResponse) {
            this.task = task
            binding.task = task
            binding.executePendingBindings()
            itemView.setOnTouchListener(this)
            itemView.setOnDragListener(DragListener())
            binding.clDelete.setOnClickListener { onItemSwipe(layoutPosition) }
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
                R.id.popup_go_done -> viewModel.moveDone(task, Status.DONE)
                R.id.popup_modify -> listener.updateDialog(task)
                R.id.popup_delete -> viewModel.deleteTask(task)
            }
            return item != null
        }

        fun getView(): View = binding.swipeView

        private fun getDeleteView(): View = binding.clDelete

        fun setVisibility(visibility: Int) {
            getDeleteView().visibility = visibility
        }

        fun getTag(): Boolean = binding.swipeView.tag as? Boolean ?: false

        fun setTag(isClamped: Boolean) {
            binding.swipeView.tag = isClamped
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onTouch(view: View?, event: MotionEvent?): Boolean {
            val shadowBuilder = View.DragShadowBuilder(view)
            val isDrag = getTag()
            when(event?.action) {
                MotionEvent.ACTION_DOWN -> { }
                MotionEvent.ACTION_MOVE -> {
                    if(!isDrag) {
                        view?.startDragAndDrop(null, shadowBuilder, view , 0)
                        return true
                    }
                }
                MotionEvent.ACTION_UP -> return true
            }
            view?.performClick()
            return false
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        viewModel.swapTask(currentList, fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(position: Int) {
        viewModel.deleteTask(getItem(position))
    }

    override fun add(type: Int, index: Int, task: TaskDetailResponse) {
        viewModel.addTask(type, index, task)
    }

    override fun remove(index: Int, task: TaskDetailResponse) {
        viewModel.remove(index, task)
    }
}

object TaskDiffCallback : DiffUtil.ItemCallback<TaskDetailResponse>() {
    override fun areItemsTheSame(
        oldItem: TaskDetailResponse,
        newItem: TaskDetailResponse,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TaskDetailResponse,
        newItem: TaskDetailResponse,
    ): Boolean {
        return oldItem == newItem
    }

}