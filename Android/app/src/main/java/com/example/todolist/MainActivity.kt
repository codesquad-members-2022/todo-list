package com.example.todolist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.history.HistoryAdapter
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val repository = ServiceLocator.provideRepository()
        tasksViewModel = ViewModelProvider(this, ViewModelFactory(repository)).get(TasksViewModel::class.java)

        val historyAdapter = HistoryAdapter()
        binding.recyclerviewHistory.adapter = historyAdapter
        binding.btnMenu.setOnClickListener {
            binding.mainLayout.openDrawer(GravityCompat.END)
            tasksViewModel.getHistories()
        }

        binding.btnClose.setOnClickListener { binding.mainLayout.closeDrawer(GravityCompat.END) }
        binding.naviView.setNavigationItemSelectedListener(this)

        tasksViewModel.historyList.observe(this) { historyAdapter.submitList(it) }
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
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}