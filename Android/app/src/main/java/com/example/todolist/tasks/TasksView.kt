package com.example.todolist.tasks

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todolist.R
import com.example.todolist.databinding.TasksViewBinding
import com.example.todolist.tasks.data.Task

class TasksView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private lateinit var binding: TasksViewBinding
    private val taskAdapter = TaskAdapter()
    init {
        initViews()
        initAttributes(attrs)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.TodoView,
            0,
            0).apply {
                try {
                    binding.todoTitle.text = getString(R.styleable.TodoView_title)
                    binding.todoBadge.text = getString(R.styleable.TodoView_badge_count)
                } finally {
                    recycle()
                }
        }
    }

    private fun initViews() {
        val layoutInflater = context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater

        binding = TasksViewBinding.inflate(layoutInflater, this, false)
        addView(binding.root)

        binding.recyclerviewTodo.adapter = taskAdapter
        binding.recyclerviewTodo.setHasFixedSize(true)
        val touchHelper = ItemTouchHelperCallback(taskAdapter)
        val helper = ItemTouchHelper(touchHelper)
        helper.attachToRecyclerView(binding.recyclerviewTodo)
    }

    fun addTasks(tasks: List<Task>) {
        taskAdapter.submitList(tasks)
    }
}