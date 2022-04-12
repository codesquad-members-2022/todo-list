package com.example.todo.respository

import com.example.todo.model.TodoItem
import com.example.todo.network.CardIndex
import retrofit2.Response

interface ToDoDataSource {
    suspend fun getTodoId(newItem: TodoItem): Response<CardIndex>
    suspend fun getInProgressId(newItem: TodoItem): Response<CardIndex>
    suspend fun getDoneId(newItem: TodoItem): Response<CardIndex>
}