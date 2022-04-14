package com.codesquad.aos.todolist.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codesquad.aos.todolist.ServiceLocator
import com.codesquad.aos.todolist.repository.card.CardItemRemoteDataSource
import com.codesquad.aos.todolist.repository.card.CardItemRepository
import com.codesquad.aos.todolist.repository.log.LogRepository
import com.codesquad.aos.todolist.ui.TodoViewModel

class ViewModelFactory (private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(TodoViewModel::class.java) -> {
                val cardItemRepository = CardItemRepository(CardItemRemoteDataSource(ServiceLocator.provideApiClient()))
                val logRepository = LogRepository(ServiceLocator.provideApiClient())
                TodoViewModel(cardItemRepository, logRepository) as T
            }

            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}