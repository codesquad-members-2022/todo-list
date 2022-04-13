package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.Status
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
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
                when (it.taskDetailResponse.status) {
                    Status.TODO -> {
                        _todoTask.value?.add(it.taskDetailResponse)
                    }
                    Status.IN_PROGRESS -> {
                        _inProgressTask.value?.add(it.taskDetailResponse)
                    }
                    Status.DONE -> {
                        _doneTask.value?.add(it.taskDetailResponse)
                    }
                }
            }
        }
    }

    fun modifyTask(modifyTaskRequest: ModifyTaskRequest) {
        viewModelScope.launch {
            taskRemoteRepository.modifyTask(modifyTaskRequest)?.let {
                when (it.status) {
                    Status.TODO -> {
                        val originalTask =
                            _todoTask.value?.find { resources -> modifyTaskRequest.id == resources.id }
                        val index = _todoTask.value?.indexOf(originalTask)
                        if (index != null) {
                            _todoTask.value?.set(index, it)
                        }
                    }
                    Status.IN_PROGRESS -> {
                        val originalTask =
                            _inProgressTask.value?.find { resources -> modifyTaskRequest.id == resources.id }
                        val index = _inProgressTask.value?.indexOf(originalTask)
                        if (index != null) {
                            _inProgressTask.value?.set(index, it)
                        }
                    }
                    Status.DONE -> {
                        val originalTask =
                            _doneTask.value?.find { resources -> modifyTaskRequest.id == resources.id }
                        val index = _doneTask.value?.indexOf(originalTask)
                        if (index != null) {
                            _doneTask.value?.set(index, it)
                        }
                    }
                }
            }
        }
    }
}