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
                    _todoTask.value = todoItem

                    inProgressItem = tasks.data.inProgress
                    _inProgressTask.value = inProgressItem

                    doneItem = tasks.data.done
                    _doneTask.value = doneItem
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

    fun loadDummyData() {}

    fun moveDone(task: TaskDetailResponse) {}

    fun deleteTask(task: TaskDetailResponse) {}

    fun swapTask(currentList: List<TaskDetailResponse>, fromPosition: Int, toPosition: Int) {}
}