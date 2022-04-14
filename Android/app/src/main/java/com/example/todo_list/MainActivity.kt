package com.example.todo_list

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todo_list.databinding.ActivityMainBinding
import com.example.todo_list.history.HistoryAdapter
import com.example.todo_list.tasks.data.Task
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

        val task1 = Task(
            1,
            "테스트하기1테스트하기1테스트하기1테스트하기1테스트하기1테스트하기1테스트하기1테스트하기1테스트하기1테스트하기1테스트하기1",
            "콘텐츠테스트1v콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1v콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1v콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1v콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1콘텐츠테스트1",
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

        val task3 = Task(
            2,
            "테스트하기3",
            "콘텐츠테스트3",
            "park",
            "todo",
            "2022-04-06T15:30:00.000+09:00",
            "2022-04-06T15:30:00.000+09:00"
        )

        binding.todoTodoView.addTasks(listOf(task1, task2, task3))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}