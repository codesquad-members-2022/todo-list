package com.example.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.model.Status
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.TaskDetailResponse
import com.example.todolist.ui.common.ViewModelFactory

class MainActivity : AppCompatActivity(), TaskAdapter.DialogListener {
    private val viewModel: TaskViewModel by viewModels { ViewModelFactory() }
    private lateinit var binding: ActivityMainBinding
    private val historyAdapter: HistoryAdapter by lazy { HistoryAdapter() }
    private val toDoAdapter: TaskAdapter by lazy { TaskAdapter(viewModel, this) }
    private val inProgressAdapter: TaskAdapter by lazy { TaskAdapter(viewModel, this) }
    private val doneAdapter: TaskAdapter by lazy { TaskAdapter(viewModel, this) }
    private val clamp: Float = 170f

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
                NewTaskDialogFragment(Status.TODO).show(supportFragmentManager, "todoDialog")
            }

            includeInProgress.btnAdd.setOnClickListener {
                NewTaskDialogFragment(Status.IN_PROGRESS).show(
                    supportFragmentManager,
                    "inProgressDialog"
                )
            }

            includeDone.btnAdd.setOnClickListener {
                NewTaskDialogFragment(Status.DONE).show(supportFragmentManager, "doneDialog")
            }
        }

        val todoItemTouchCallback = ItemTouchCallback(clamp, toDoAdapter)
        with(binding.includeTodo.rvTodo) {
            ItemTouchHelper(todoItemTouchCallback).attachToRecyclerView(this)
            adapter = toDoAdapter
            setOnTouchListener { _, _ ->
                todoItemTouchCallback.removePreviousClamp(this)
                false
            }
        }
        viewModel.todoTask.observe(this) { todoTask ->
            toDoAdapter.submitList(todoTask.toList())
        }

        val inProgressItemTouchCallback = ItemTouchCallback(clamp, inProgressAdapter)
        with(binding.includeInProgress.rvInProgress) {
            ItemTouchHelper(inProgressItemTouchCallback).attachToRecyclerView(this)
            adapter = inProgressAdapter
            setOnTouchListener { _, _ ->
                inProgressItemTouchCallback.removePreviousClamp(this)
                false
            }
        }
        viewModel.inProgressTask.observe(this) { inProgressTask ->
            inProgressAdapter.submitList(inProgressTask.toList())
        }



        binding.includeDone.rvDone.adapter = doneAdapter
        val doneItemTouchCallback = ItemTouchCallback(clamp, doneAdapter)
        with(binding.includeDone.rvDone) {
            ItemTouchHelper(doneItemTouchCallback).attachToRecyclerView(this)
            adapter = doneAdapter
            setOnTouchListener { _, _ ->
                doneItemTouchCallback.removePreviousClamp(this)
                false
            }
        }
        viewModel.doneTask.observe(this) { doneTask ->
            doneAdapter.submitList(doneTask.toList())
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

    override fun updateDialog(task: TaskDetailResponse) {
        UpdateTaskDialogFragment(task).show(supportFragmentManager, "updateDialog")
    }
}
