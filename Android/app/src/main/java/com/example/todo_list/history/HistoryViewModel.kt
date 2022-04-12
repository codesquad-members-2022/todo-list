package com.example.todo_list.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_list.Repository
import com.example.todo_list.history.data.HistoryCard
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: Repository): ViewModel() {
    private val _historyList = MutableLiveData<List<HistoryCard>>()
    val historyList: LiveData<List<HistoryCard>> get() = _historyList

    private val _checkLoading = MutableLiveData<Boolean>()
    val checkLoading: LiveData<Boolean> get() = _checkLoading

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
}