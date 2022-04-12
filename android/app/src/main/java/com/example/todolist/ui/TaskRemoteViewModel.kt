package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.Status
import com.example.todolist.model.Task
import com.example.todolist.model.response.TaskDetailResponse
import com.example.todolist.repository.TaskRemoteRepository
import kotlinx.coroutines.launch

class TaskRemoteViewModel(private val taskRemoteRepository: TaskRemoteRepository) : ViewModel() {

    private val _todoTask = MutableLiveData<MutableList<TaskDetailResponse>>()
    val todoTask: LiveData<MutableList<TaskDetailResponse>>
        get() = _todoTask

    private val _inProgressTask = MutableLiveData<MutableList<TaskDetailResponse>>()
    val inProgressTask: LiveData<MutableList<TaskDetailResponse>>
        get() = _inProgressTask

    private val _doneTask = MutableLiveData<MutableList<TaskDetailResponse>>()
    val doneTask: LiveData<MutableList<TaskDetailResponse>>
        get() = _doneTask

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            val tasks = taskRemoteRepository.loadTask()
            _todoTask.value = tasks?.todo
            _inProgressTask.value = tasks?.inProgress
            _doneTask.value = tasks?.done
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRemoteRepository.addTask(task)?.let {
                when (it.resources.status) {
                    Status.TODO -> {
                        _todoTask.value?.add(it.resources)
                    }
                    Status.IN_PROGRESS -> {
                        _inProgressTask.value?.add(it.resources)
                    }
                    Status.DONE -> {
                        _doneTask.value?.add(it.resources)
                    }
                }
            }
        }
    }
}