package com.example.todolist.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.repository.TaskRemoteDataSource
import com.example.todolist.repository.TaskRemoteRepository
import com.example.todolist.repository.TaskRepository
import com.example.todolist.ui.TaskRemoteViewModel
import com.example.todolist.ui.TaskViewModel

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TaskViewModel::class.java) -> TaskViewModel(TaskRepository()) as T
            modelClass.isAssignableFrom(TaskRemoteViewModel::class.java) -> {
                val repository = TaskRemoteRepository(TaskRemoteDataSource())
                TaskRemoteViewModel(repository) as T
            }
            else -> throw IllegalAccessException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}