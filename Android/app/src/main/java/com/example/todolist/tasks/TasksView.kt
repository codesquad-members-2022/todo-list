package com.example.todolist.tasks

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todolist.R
import com.example.todolist.TasksViewModel
import com.example.todolist.databinding.TasksViewBinding
import com.example.todolist.tasks.data.Task

class TasksView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    var viewModel: TasksViewModel? = null
    private lateinit var binding: TasksViewBinding
    private lateinit var todosAdapter: TaskAdapter
    init {
        initViews()
        initAttributes(attrs)
    }

    interface OnAddEditTaskListener {
        fun onSubmitTask(title: String, contents: String, status: String)
    }
    var setOnAddEditTaskListener: OnAddEditTaskListener? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        viewModel?.let {
            todosAdapter = TaskAdapter(it)
        } ?: throw Exception("viewModel을 설정해야 합니다.")
        setRecyclerView()
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
        binding.recyclerviewTodo.adapter = todosAdapter
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
                    setOnAddEditTaskListener?.onSubmitTask(
                        result["title"].toString(),
                        result["body"].toString(),
                        tag as String
                    )
                }
            }
        }

        dialog.show(
            (context as FragmentActivity).supportFragmentManager,
            "taskDialog"
        )
    }

    fun addTasks(tasks: List<Task>) {
        binding.todoBadge.text = tasks.size.toString()
        todosAdapter.submitList(tasks.toList()) {
            binding.recyclerviewTodo.smoothScrollToPosition(0)
        }
    }
}
