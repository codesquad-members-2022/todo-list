package com.example.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.ui.common.TodoTouchHelper
import com.example.todolist.ui.ongoing.OngoingAdapter
import com.example.todolist.ui.todo.TodoAdapter

// 해야할 일 뷰 어뎁터, 뷰모델

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
        val ongoingAdapter = OngoingAdapter(viewModel)
        binding.rvProgress.adapter = ongoingAdapter

        setOnClickMenu()
        setOnClickTodoAdd()

        val swipeHelperCallback = TodoTouchHelper()
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvTodo)
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvProgress)

        viewModel.loadAllTask()

        viewModel.todoTaskList.observe(this) {
            todoAdapter.submitList(it.toList()) {
                if (viewModel.state == 1) {
                    binding.rvTodo.scrollToPosition(0)
                }
            }
        }

        viewModel.onGoingTaskList.observe(this) {
            ongoingAdapter.submitList(it.toList()) {
                if (viewModel.state == 1) {
                    binding.rvTodo.scrollToPosition(0)
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
            /*Log.d("AppTest", "click btn")
            viewModel.addTodo()*/
        }

        binding.btnProgressAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, null)
            /*Log.d("AppTest", "click btn")
            viewModel.addTodo()*/
        }
    }

    private fun setOnClickMenu() {
        binding.topAppbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.log -> {
                    Log.d("AppTest", "${it.itemId}")
                    if (!binding.dloAppbar.isDrawerOpen(GravityCompat.END)) {
                        binding.dloAppbar.openDrawer(GravityCompat.END)
                    }
                }

                else -> {
                    Log.d("AppTest", "${it.itemId}")
//                    if(binding.dloAppbar.isDrawerOpen(GravityCompat.END)) {
//                        binding.dloAppbar.closeDrawer(GravityCompat.END)
//                    }
                }
            }
            true
        }
    }
}