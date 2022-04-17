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
import com.example.todolist.tasks.TasksView
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
        binding.recyclerviewHistory.adapter = historyAdapter

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

        tasksViewModel.getAllTasks()

        binding.todoTodoView.setOnAddEditTaskListener = (object : TasksView.OnAddEditTaskListener{
            override fun onSubmitTask(title: String, contents: String, status: String) {
                tasksViewModel.addTask(title, contents, "sam", status)
            }
        })

        binding.todoInprogressView.setOnAddEditTaskListener = (object : TasksView.OnAddEditTaskListener{
            override fun onSubmitTask(title: String, contents: String, status: String) {
                tasksViewModel.addTask(title, contents, "sam", status)
            }
        })

        binding.todoDoneView.setOnAddEditTaskListener = (object : TasksView.OnAddEditTaskListener{
            override fun onSubmitTask(title: String, contents: String, status: String) {
                tasksViewModel.addTask(title, contents, "sam", status)
            }
        })

        binding.todoTodoView.viewModel = tasksViewModel
        binding.todoInprogressView.viewModel = tasksViewModel
        binding.todoDoneView.viewModel = tasksViewModel
        tasksViewModel.tasksList.observe(this) { result ->
            binding.todoTodoView.addTasks(
                result.filter { it.status == "todo" }
            )

            binding.todoInprogressView.addTasks(
                result.filter { it.status == "doing" }
            )

            binding.todoDoneView.addTasks(
                result.filter { it.status == "done" }
            )
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

}