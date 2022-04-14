package com.example.todo.respository

import android.util.Log
import com.example.todo.model.TodoItem
import com.example.todo.network.AddPostBody
import com.example.todo.network.CardIndex
import com.example.todo.network.RetrofitClient
import com.example.todo.network.TodoResponse
import retrofit2.Response

class ToDoRemoteDataSource(private val retrofitClient: RetrofitClient) : ToDoDataSource {
    override suspend fun getTodoId(newItem: TodoItem): Response<CardIndex> {
        val body = AddPostBody(newItem.title, newItem.content, "todo", "Jay")
        return retrofitClient.getCardIdx(body)
    }

    override suspend fun getInProgressId(newItem: TodoItem): Response<CardIndex> {
        val body = AddPostBody(newItem.title, newItem.content, "inProgress", "Jay")
        return retrofitClient.getCardIdx(body)
    }

    override suspend fun getDoneId(newItem: TodoItem): Response<CardIndex> {
        val body = AddPostBody(newItem.title, newItem.content, "done", "Jay")
        return retrofitClient.getCardIdx(body)
    }

    override suspend fun getTodoItems(): Response<TodoResponse> {
        val response = retrofitClient.getTodos()
        Log.d("test", response.isSuccessful.toString())
        return response
    }

    override suspend fun removeItem(cardId: Int): Response<Void> {
        val response = retrofitClient.removeTodo(cardId)
        Log.d("testDelete", response.isSuccessful.toString())
        return response
    }
}