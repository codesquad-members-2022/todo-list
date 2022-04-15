package com.example.todo.respository

import com.example.todo.model.TodoItem
import com.example.todo.network.CardIndex
import com.example.todo.network.TodoResponse
import com.example.todo.network.UpdateTodoBody
import retrofit2.Response

interface ToDoDataSource {
    suspend fun getTodoId(newItem: TodoItem): Response<CardIndex>
    suspend fun getInProgressId(newItem: TodoItem): Response<CardIndex>
    suspend fun getDoneId(newItem: TodoItem): Response<CardIndex>
    suspend fun getTodoItems(): Response<TodoResponse>
    suspend fun removeItem(cardId: Int): Response<Void>
    suspend fun updateItem(cardId: Int, body: UpdateTodoBody): Response<Void>
    suspend fun moveItem(
        itemId: Int,
        type: String,
        prevItemId: Int?,
        nextItemId: Int?
    ): Response<Void>
}