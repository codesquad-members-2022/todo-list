package com.example.todolist.ui

import androidx.lifecycle.LiveData
import com.example.todolist.network.Result
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.History
import com.example.todolist.model.Status
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.TaskDetailResponse
import com.example.todolist.repository.TaskRemoteRepository
import kotlinx.coroutines.launch
import java.util.*

class TaskRemoteViewModel(private val taskRemoteRepository: TaskRemoteRepository) : ViewModel() {

    private val _history = MutableLiveData<List<History>>()
    val history: LiveData<List<History>>
        get() = _history

    private var todoItem: MutableList<TaskDetailResponse> = mutableListOf()
    private val _todoTask = MutableLiveData<MutableList<TaskDetailResponse>>()
    val todoTask: LiveData<MutableList<TaskDetailResponse>>
        get() = _todoTask

    private var inProgressItem: MutableList<TaskDetailResponse> = mutableListOf()
    private val _inProgressTask = MutableLiveData<MutableList<TaskDetailResponse>>()
    val inProgressTask: LiveData<MutableList<TaskDetailResponse>>
        get() = _inProgressTask

    private var doneItem: MutableList<TaskDetailResponse> = mutableListOf()
    private val _doneTask = MutableLiveData<MutableList<TaskDetailResponse>>()
    val doneTask: LiveData<MutableList<TaskDetailResponse>>
        get() = _doneTask

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            when (val tasks = taskRemoteRepository.loadTask()) {
                is Result.Success -> {
                    todoItem = tasks.data.todo
                    _todoTask.value = tasks.data.todo

                    inProgressItem = tasks.data.inProgress
                    _inProgressTask.value = tasks.data.inProgress

                    doneItem = tasks.data.done
                    _doneTask.value = tasks.data.done
                }
                is Result.Error -> _error.value = tasks.error
            }
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            when (val tasks = taskRemoteRepository.addTask(task)) {
                is Result.Success -> {
                    when (tasks.data.taskDetailResponse.status) {
                        Status.TODO -> {
                            todoItem.add(0, tasks.data.taskDetailResponse)
                            _todoTask.value = todoItem
                        }
                        Status.IN_PROGRESS -> {
                            inProgressItem.add(0, tasks.data.taskDetailResponse)
                            _inProgressTask.value = inProgressItem
                        }
                        Status.DONE -> {
                            doneItem.add(0, tasks.data.taskDetailResponse)
                            _doneTask.value = doneItem
                        }
                    }
                }
                is Result.Error -> _error.value = tasks.error
            }
        }
    }

    fun modifyTask(modifyTaskRequest: ModifyTaskRequest) {
        viewModelScope.launch {
            when (val tasks = taskRemoteRepository.modifyTask(modifyTaskRequest)) {
                is Result.Success -> {
                    when (tasks.data.taskDetailResponse.status) {
                        Status.TODO -> {
                            val originalTask = _todoTask.value?.find { resources ->
                                modifyTaskRequest.id == resources.id
                            }
                            _todoTask.value?.indexOf(originalTask)?.let { index ->
                                todoItem[index] = tasks.data.taskDetailResponse
                                _todoTask.value = todoItem
                            }
                        }
                        Status.IN_PROGRESS -> {
                            val originalTask =
                                _inProgressTask.value?.find { resources -> modifyTaskRequest.id == resources.id }
                            _inProgressTask.value?.indexOf(originalTask)?.let { index ->
                                inProgressItem[index] = tasks.data.taskDetailResponse
                                _inProgressTask.value = inProgressItem
                            }
                        }
                        Status.DONE -> {
                            val originalTask =
                                _doneTask.value?.find { resources -> modifyTaskRequest.id == resources.id }
                            _doneTask.value?.indexOf(originalTask)?.let { index ->
                                doneItem[index] = tasks.data.taskDetailResponse
                                _doneTask.value = doneItem
                            }
                        }
                    }
                }
            }
        }
    }

    fun loadHistory() {
        viewModelScope.launch {
            when (val history = taskRemoteRepository.loadHistory()) {
                is Result.Success -> {
                    _history.value = history.data
                }
                is Result.Error -> {
                    _error.value = history.error
                }
            }
        }
    }

    fun deleteTask(task: TaskDetailResponse) {
        viewModelScope.launch {
            when (val deleteTask = taskRemoteRepository.deleteTask(task.id)) {
                is Result.Success -> {
                    when (deleteTask.data.status) {
                        Status.TODO -> {
                            val originalTask = todoItem.find { it.id == deleteTask.data.id }
                            val index = todoItem.indexOf(originalTask)
                            if (index != -1) todoItem.removeAt(index)
                            _todoTask.value = todoItem
                        }
                        Status.IN_PROGRESS -> {
                            val originalTask = inProgressItem.find { it.id == deleteTask.data.id }
                            val index = inProgressItem.indexOf(originalTask)
                            if (index != -1) inProgressItem.removeAt(index)
                            _inProgressTask.value = inProgressItem
                        }
                        Status.DONE -> {
                            val originalTask = doneItem.find { it.id == deleteTask.data.id }
                            val index = doneItem.indexOf(originalTask)
                            if (index != -1) doneItem.removeAt(index)
                            _doneTask.value = doneItem
                        }
                    }
                }
                is Result.Error -> {
                    _error.value = deleteTask.error
                }
            }
        }
    }

    fun moveDone(task: TaskDetailResponse, status: Status) {
        viewModelScope.launch {
            when (val updateTask = taskRemoteRepository.moveTask(task, status)) {
                is Result.Success -> {
                    when (task.status) {
                        Status.TODO -> {
                            todoItem.remove(task)
                            _todoTask.value = todoItem
                        }
                        Status.IN_PROGRESS -> {
                            inProgressItem.remove(task)
                            _inProgressTask.value = inProgressItem
                        }
                        Status.DONE -> {
                            doneItem.remove(task)
                            _doneTask.value = doneItem
                        }
                    }

                    when (updateTask.data.status) {
                        Status.TODO -> {
                            todoItem.add(0, updateTask.data)
                            _todoTask.value = todoItem
                        }
                        Status.IN_PROGRESS -> {
                            inProgressItem.add(0, updateTask.data)
                            _inProgressTask.value = inProgressItem
                        }
                        Status.DONE -> {
                            doneItem.add(0, updateTask.data)
                            _doneTask.value = doneItem
                        }
                    }
                }
                is Result.Error -> {
                    _error.value = updateTask.error
                }
            }
        }
    }

    fun swapTask(currentList: List<TaskDetailResponse>, fromPosition: Int, toPosition: Int) {
        when (currentList) {
            todoItem -> {
                Collections.swap(todoItem, fromPosition, toPosition)
                _todoTask.value = todoItem
            }
            inProgressItem -> {
                Collections.swap(inProgressItem, fromPosition, toPosition)
                _inProgressTask.value = inProgressItem
            }
            doneItem -> {
                Collections.swap(doneItem, fromPosition, toPosition)
                _doneTask.value = doneItem
            }
        }
    }

}