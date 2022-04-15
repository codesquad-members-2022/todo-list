package com.example.todo_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list.data.Repository
import com.example.todo_list.history.data.HistoryCard
import com.example.todo_list.tasks.data.Task
import kotlinx.coroutines.launch

class TasksViewModel(private val repository: Repository): ViewModel() {
    private val _historyList = MutableLiveData<List<HistoryCard>>()
    val historyList: LiveData<List<HistoryCard>> get() = _historyList

    private val _checkLoading = MutableLiveData<Boolean>()
    val checkLoading: LiveData<Boolean> get() = _checkLoading

    private val _taskList = MutableLiveData<List<Task>>()
    val taskList: LiveData<List<Task>> get() = _taskList

    init {
        getAllTasks()
    }

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

    fun getAllTasks() {
        viewModelScope.launch {
            val response = repository.getAllTasks()
            if (response.isSuccessful) {
                _taskList.value = response.body()
            }
        }
    }
}