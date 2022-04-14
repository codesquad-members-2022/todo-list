package com.example.todolist.tasks

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
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
        setRecyclerView()

        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        binding.btnTaskAdd.setOnClickListener {
            val prev = fragmentManager.findFragmentByTag("taskDialog")
            if (prev != null) {
                transaction.remove(prev)
            }
            transaction.addToBackStack(null)
            showDialog(fragmentManager)
        }
    }

    private fun setRecyclerView() {
        binding.recyclerviewTodo.adapter = taskAdapter
        val touchHelper = ItemTouchHelperCallback()
        val helper = ItemTouchHelper(touchHelper)
        helper.attachToRecyclerView(binding.recyclerviewTodo)
    }

    private fun showDialog(fragmentManager: FragmentManager) {
        val dialog = TaskDialogFragment()
        dialog.arguments = bundleOf(
            "action" to DialogAction.ADD
        )
        fragmentManager.setFragmentResultListener("addTask", context as FragmentActivity) { resultKey, result ->
            when (resultKey) {
                "addTask" -> {
                    Log.d("AAAA", result["title"].toString())
                    Log.d("AAAA", result["body"].toString())
                }
            }
        }

        dialog.show(
            (context as FragmentActivity).supportFragmentManager,
            "taskDialog"
        )
    }

    fun addTasks(tasks: List<Task>) {
        taskAdapter.submitList(tasks)
    }
}
