package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.model.*
import com.example.todolist.model.ActionType.*
import com.example.todolist.repository.TaskRepository

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _history = MutableLiveData<List<History>>()
    val history: LiveData<List<History>>
        get() = _history

    private val _todoTask = MutableLiveData<List<TaskResponse>>()
    val todoTask: LiveData<List<TaskResponse>>
        get() = _todoTask

    private val _inProgressTask = MutableLiveData<List<TaskResponse>>()
    val inProgressTask: LiveData<List<TaskResponse>>
        get() = _inProgressTask

    private val _doneTask = MutableLiveData<List<TaskResponse>>()
    val doneTask: LiveData<List<TaskResponse>>
        get() = _doneTask

    init {
        loadTasks()
    }

    private fun loadTasks() {
        val tasks = repository.getTasks()
        _todoTask.value = tasks.todo
        _inProgressTask.value = tasks.inProgress
        _doneTask.value = tasks.done
    }

    fun loadDummyData() {
        _history.value = repository.getHistory()
    }

    fun addTodoTask(task: Task) {
        val tasks = repository.addTask(task)
        _todoTask.value = tasks.todo
    }

    fun addInProgressTask(task: Task) {
        val tasks = repository.addTask(task)
        _inProgressTask.value = tasks.inProgress
    }

    fun addDoneTask(task: Task) {
        val tasks = repository.addTask(task)
        _doneTask.value = tasks.done
    }
}