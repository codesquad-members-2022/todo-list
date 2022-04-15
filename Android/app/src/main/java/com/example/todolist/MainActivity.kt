package com.example.todolist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.history.HistoryAdapter
import com.example.todolist.tasks.TaskAdapter
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

        binding.todoTodoView.addTasks(tasksViewModel.getSomeTasks())
        tasksViewModel.taskList.observe(this) {
            taskAdapter.submitList(it)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

}