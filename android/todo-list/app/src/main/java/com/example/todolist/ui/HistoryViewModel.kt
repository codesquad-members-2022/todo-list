package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.History
import com.example.todolist.data.HistoryRemoteDataSource
import com.example.todolist.data.HistoryRepository
import com.example.todolist.network.ApiClient
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    private val historyRepository = HistoryRepository(HistoryRemoteDataSource(ApiClient.create()))
    private val _historyList = MutableLiveData<List<History>>()
    val historyList: LiveData<List<History>> = _historyList

    fun loadHistoryList() {
        viewModelScope.launch {
            val history = historyRepository.getHistories()
            history?.reversed()?.let {
                _historyList.value = it
            }
        }
    }



}