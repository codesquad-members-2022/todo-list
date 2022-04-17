package com.example.todolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Repository
import com.example.todolist.history.data.HistoryCard
import com.example.todolist.tasks.data.Task
import kotlinx.coroutines.launch

class TasksViewModel(private val repository: Repository): ViewModel() {
    private val _historyList = MutableLiveData<List<HistoryCard>>()
    val historyList: LiveData<List<HistoryCard>> get() = _historyList

    private val _checkLoading = MutableLiveData<Boolean>()
    val checkLoading: LiveData<Boolean> get() = _checkLoading

    private val _tasksList = MutableLiveData<List<Task>>()
    val tasksList: LiveData<List<Task>> get() = _tasksList

    fun getHistories() {
        _checkLoading.value = true
        viewModelScope.launch {
            val response = repository.getHistories()
            if (response.isSuccessful) {
                _historyList.value = response.body()
                _checkLoading.value = false
            }
        }
    }

    fun addTask(title: String, contents: String, user: String, status: String) {
        viewModelScope.launch {
            repository.createTask(title, contents, user, status)
            _tasksList.value = repository.getAllTasks().body()
        }
    }

    fun getAllTasks() {
        viewModelScope.launch {
            val response = repository.getAllTasks()
            if (response.isSuccessful) {
                _tasksList.value = response.body()
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task.id)
            _tasksList.value = repository.getAllTasks().body()
        }
    }
}