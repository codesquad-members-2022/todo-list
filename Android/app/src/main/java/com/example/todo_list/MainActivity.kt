package com.example.todo_list

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.DragEvent
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.databinding.ActivityMainBinding
import com.example.todo_list.history.HistoryAdapter
import com.example.todo_list.tasks.TaskAdapter
import com.example.todo_list.tasks.TasksView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tasksViewModel: TasksViewModel
    private lateinit var recyclerViewTodo: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = ServiceLocator.provideRepository()
        tasksViewModel =
            ViewModelProvider(this, ViewModelFactory(repository)).get(TasksViewModel::class.java)

        recyclerViewTodo = findViewById(R.id.recyclerview_todo)

        val historyAdapter = HistoryAdapter()
        val taskAdapter = TaskAdapter()

        binding.recyclerviewHistory.adapter = historyAdapter
        recyclerViewTodo.adapter = taskAdapter

        binding.btnMenu.setOnClickListener {
            binding.mainLayout.openDrawer(GravityCompat.END)
            tasksViewModel.getHistories()
        }

        binding.btnClose.setOnClickListener { binding.mainLayout.closeDrawer(GravityCompat.END) }
        binding.naviView.setNavigationItemSelectedListener(this)

        tasksViewModel.historyList.observe(this) {
            historyAdapter.submitList(it)
        }
        tasksViewModel.checkLoading.observe(this) {
            if (it) {
                binding.spinner.visibility = View.VISIBLE
                binding.recyclerviewHistory.visibility = View.GONE
            } else {
                binding.spinner.visibility = View.GONE
                binding.recyclerviewHistory.visibility = View.VISIBLE
            }
        }

        tasksViewModel.taskList.observe(this) {
            taskAdapter.submitList(it)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

}