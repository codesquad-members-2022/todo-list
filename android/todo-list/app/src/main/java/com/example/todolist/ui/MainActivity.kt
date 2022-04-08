package com.example.todolist.ui

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
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
        val adapter = TodoAdapter()
        binding.rvTodo.adapter = adapter
        setOnClickMenu()
        setOnClickTodoAdd()

        viewModel.todoTaskList.observe(this) {
            Log.d("AppTest", "observer")
            adapter.submitList(it) {
                binding.rvTodo.smoothScrollToPosition(0)
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
            val dialog = CreateCardDialogFragment(binding.rvTodo)
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