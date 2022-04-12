package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.model.*
import com.example.todolist.repository.TaskRepository

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _history = MutableLiveData<List<History>>()
    val history: LiveData<List<History>>
        get() = _history

    private val _todoTask = MutableLiveData<List<TaskDetailResponse>>()
    val todoTask: LiveData<List<TaskDetailResponse>>
        get() = _todoTask

    private val _inProgressTask = MutableLiveData<List<TaskDetailResponse>>()
    val inProgressTask: LiveData<List<TaskDetailResponse>>
        get() = _inProgressTask

    private val _doneTask = MutableLiveData<List<TaskDetailResponse>>()
    val doneTask: LiveData<List<TaskDetailResponse>>
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

    fun moveDone(task: TaskDetailResponse) {
        val tasks = repository.moveDone(task)
        _todoTask.value = tasks.todo
        _inProgressTask.value = tasks.inProgress
        _doneTask.value = tasks.done
    }

    fun deleteTask(task: TaskDetailResponse) {
        val tasks = repository.deleteTask(task)
        _todoTask.value = tasks.todo
        _inProgressTask.value = tasks.inProgress
        _doneTask.value = tasks.done
    }

    fun updateTodoTask(task: TaskDetailResponse) {
        val tasks = repository.updateTask(task)
        _todoTask.value = tasks.todo
    }

    fun updateInProgressTask(task: TaskDetailResponse) {
        val tasks = repository.updateTask(task)
        _inProgressTask.value = tasks.inProgress
    }

    fun updateDoneTask(task: TaskDetailResponse) {
        val tasks = repository.updateTask(task)
        _doneTask.value = tasks.done
    }
}