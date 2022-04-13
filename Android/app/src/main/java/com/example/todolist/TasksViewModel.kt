package com.example.todolist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Repository
import com.example.todolist.history.data.HistoryCard
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

    fun getSomeTasks(): List<Task> {
        val task1 = Task(
            1,
            "테스트하기1",
            "콘텐츠테스트1",
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

        return listOf(task1, task2)
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