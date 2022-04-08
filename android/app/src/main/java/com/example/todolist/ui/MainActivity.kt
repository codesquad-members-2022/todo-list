package com.example.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.Status
import com.example.todolist.ui.common.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel: TaskViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: ActivityMainBinding
    private val historyAdapter: HistoryAdapter by lazy { HistoryAdapter() }
    private val toDoAdapter: TaskAdapter by lazy { TaskAdapter() }
    private val inProgressAdapter: TaskAdapter by lazy { TaskAdapter() }
    private val doneAdapter: TaskAdapter by lazy { TaskAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        onDrawerEvent()

        binding.rvHistory.adapter = historyAdapter
        viewModel.history.observe(this) { histories ->
            historyAdapter.submitList(histories)
        }

        with(binding) {
            includeTodo.btnAdd.setOnClickListener {
                Dialog(Status.TODO).show(supportFragmentManager, "todoDialog")
            }

            includeInProgress.btnAdd.setOnClickListener {
                Dialog(Status.IN_PROGRESS).show(supportFragmentManager, "inProgressDialog")
            }

            includeDone.btnAdd.setOnClickListener {
                Dialog(Status.DONE).show(supportFragmentManager, "doneDialog")
            }
        }

        binding.includeTodo.rvTodo.adapter = toDoAdapter
        viewModel.todoTask.observe(this) { todoTask ->
            toDoAdapter.submitList(todoTask)
        }

        binding.includeInProgress.rvInProgress.adapter = inProgressAdapter
        viewModel.inProgressTask.observe(this) { inProgressTask ->
            inProgressAdapter.submitList(inProgressTask)
        }

        binding.includeDone.rvDone.adapter = doneAdapter
        viewModel.doneTask.observe(this) { doneTask ->
            doneAdapter.submitList(doneTask)
        }

    }

    private fun onDrawerEvent() {
        binding.btnDrawer.setOnClickListener {
            viewModel.loadDummyData()
            binding.dlDrawer.openDrawer(Gravity.RIGHT)
        }

        binding.btnClose.setOnClickListener {
            binding.dlDrawer.closeDrawer(Gravity.RIGHT)
        }
    }
}
