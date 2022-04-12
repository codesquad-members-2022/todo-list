package com.example.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo_list.data.Repository
import com.example.todo_list.history.HistoryViewModel

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            HistoryViewModel(repository) as T
        } else {
            throw Exception("클래스가 존재하지 않습니다.")
        }
    }
}