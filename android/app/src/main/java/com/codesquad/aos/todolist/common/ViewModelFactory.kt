package com.codesquad.aos.todolist.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codesquad.aos.todolist.ServiceLocator
import com.codesquad.aos.todolist.repository.CardItemRemoteDataSource
import com.codesquad.aos.todolist.repository.CardItemRepository
import com.codesquad.aos.todolist.ui.TodoViewModel

class ViewModelFactory (private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TodoViewModel::class.java) -> {
                val repository = CardItemRepository(CardItemRemoteDataSource(ServiceLocator.provideApiClient()))
                TodoViewModel(repository) as T
            }

            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}