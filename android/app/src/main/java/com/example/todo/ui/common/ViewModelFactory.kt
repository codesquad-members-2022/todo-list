package com.example.todo.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todo.network.RetrofitClient
import com.example.todo.respository.ActionLogRemoteDataSource
import com.example.todo.respository.ActionLogRepository
import com.example.todo.ui.toDo.ToDoViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ToDoViewModel::class.java)) {
            val repository =
                ActionLogRepository(context, ActionLogRemoteDataSource(RetrofitClient.create()))
            ToDoViewModel(repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}