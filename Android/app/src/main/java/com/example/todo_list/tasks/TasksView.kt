package com.example.todo_list.tasks

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R
import com.example.todo_list.tasks.data.Task

class TasksView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private lateinit var tvTitle: TextView
    private lateinit var tvBadgeCount: TextView
    private lateinit var btnAddTask: ImageButton
    private lateinit var recyclerViewTodo: RecyclerView
    private val todosAdapter = TodoAdapter()
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
                    tvTitle.text = getString(R.styleable.TodoView_title)
                    tvBadgeCount.text = getString(R.styleable.TodoView_badge_count)
                } finally {
                    recycle()
                }
        }
    }

    private fun initViews() {
        val layoutInflater = context.getSystemService(
            Context.LAYOUT_INFLATER_SERVICE
        ) as LayoutInflater

        addView(
            layoutInflater.inflate(R.layout.tasks_view, this, false)
        )

        tvTitle = findViewById(R.id.todo_title)
        tvBadgeCount = findViewById(R.id.todo_badge)
        btnAddTask = findViewById(R.id.btn_task_add)
        recyclerViewTodo = findViewById(R.id.recyclerview_todo)

        recyclerViewTodo.adapter = todosAdapter
        recyclerViewTodo.setHasFixedSize(true)
        val touchHelper = ItemTouchHelperCallback(todosAdapter)
        val helper = ItemTouchHelper(touchHelper)
        helper.attachToRecyclerView(recyclerViewTodo)
    }

    fun addTasks(tasks: List<Task>) {
        todosAdapter.submitList(tasks)
    }
}