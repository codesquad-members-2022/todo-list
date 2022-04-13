package com.example.todolist.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.ui.common.TodoTouchHelper
import com.example.todolist.ui.todo.TodoAdapter

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoAdapter = TodoAdapter(viewModel)
        binding.rvTodo.adapter = todoAdapter
        val ongoingAdapter = TodoAdapter(viewModel)
        binding.rvProgress.adapter = ongoingAdapter
        val complete = TodoAdapter(viewModel)
        binding.rvDone.adapter = complete
        setOnClickMenu()
        setOnClickTodoAdd()

        val swipeHelperCallback = TodoTouchHelper()
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvTodo)
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvProgress)
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvDone)

        viewModel.todoTaskList.observe(this) {
            todoAdapter.submitList(it.toList()) {
                if (viewModel.state == 1) {
                    binding.rvTodo.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.onGoingTaskList.observe(this) {
            ongoingAdapter.submitList(it.toList()) {
                if (viewModel.state == 1) {
                    binding.rvTodo.smoothScrollToPosition(0)
                }
            }
        }

        viewModel.completeTaskList.observe(this) {
            complete.submitList(it.toList()) {
                if (viewModel.state == 1) {
                    binding.rvDone.smoothScrollToPosition(0)
                }
            }

        }
    }

    override fun onBackPressed() {
        if (binding.dloAppbar.isDrawerOpen(GravityCompat.END)) {
            binding.dloAppbar.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    private fun setOnClickTodoAdd() {
        binding.btnTodoAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, null)
        }

        binding.btnProgressAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, null)
        }

        binding.btnDoneAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, null)
        }
    }

    private fun setOnClickMenu() {
        binding.topAppbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.log -> {
                    if (!binding.dloAppbar.isDrawerOpen(GravityCompat.END)) {
                        binding.dloAppbar.openDrawer(GravityCompat.END)
                    }
                }

                else -> {
//                    if(binding.dloAppbar.isDrawerOpen(GravityCompat.END)) {
//                        binding.dloAppbar.closeDrawer(GravityCompat.END)
//                    }
                }
            }
            true
        }
    }
}