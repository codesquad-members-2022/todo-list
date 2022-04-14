package com.example.todolist

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.data.TasksRepository
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.history.HistoryAdapter
import com.example.todolist.history.HistoryViewModel
import com.example.todolist.tasks.data.Task
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        historyViewModel = ViewModelProvider(this, ViewModelFactory(TasksRepository())).get(HistoryViewModel::class.java)

        val historyAdapter = HistoryAdapter()
        binding.recyclerviewHistory.adapter = historyAdapter
        binding.btnMenu.setOnClickListener {
            binding.mainLayout.openDrawer(GravityCompat.END)
            historyViewModel.getHistories()
        }

        binding.btnClose.setOnClickListener { binding.mainLayout.closeDrawer(GravityCompat.END) }
        binding.naviView.setNavigationItemSelectedListener(this)

        historyViewModel.historyList.observe(this) { historyAdapter.submitList(it) }
        historyViewModel.checkLoading.observe(this) {
            if (it) {
                binding.spinner.visibility = View.VISIBLE
                binding.recyclerviewHistory.visibility = View.GONE
            } else {
                binding.spinner.visibility = View.GONE
                binding.recyclerviewHistory.visibility = View.VISIBLE
            }
        }

        val task1 = Task(
            1,
            "테스트하기1",
            "콘텐츠테스트1",
            "jung",
            "doing",
            "2022-04-06T15:30:00.000+09:00",
            "2022-04-06T15:30:00.000+09:00"
        )

        val task2 = Task(
            2,
            "테스트하기2",
            "콘텐츠테스트2",
            "park",
            "todo",
            "2022-04-06T15:30:00.000+09:00",
            "2022-04-06T15:30:00.000+09:00"
        )

        binding.todoTodoView.addTasks(listOf(task1, task2))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}