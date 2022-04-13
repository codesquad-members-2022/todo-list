package com.example.todo.respository

import android.util.Log
import com.example.todo.model.TodoItem
import com.example.todo.network.CardIndex
import com.example.todo.network.RetrofitClient
import com.example.todo.network.TodoResponse
import retrofit2.Response

class ToDoRemoteDataSource(private val retrofitClient: RetrofitClient) : ToDoDataSource {
    override suspend fun getTodoId(newItem: TodoItem): Response<CardIndex> {
        return retrofitClient.getCardIdx(newItem.title, newItem.content, "Jay", "todo")
    }

    override suspend fun getInProgressId(newItem: TodoItem): Response<CardIndex> {
        return retrofitClient.getCardIdx(newItem.title, newItem.content, "Jay", "inProgress")
    }

    override suspend fun getDoneId(newItem: TodoItem): Response<CardIndex> {
        return retrofitClient.getCardIdx(newItem.title, newItem.content, "Jay", "done")
        //val result = retrofitClient.getCardIdx(newItem.title, newItem.content, "Jay", "done")
    }

    override suspend fun getTodoItems(): Response<TodoResponse> {
        val response =  retrofitClient.getTodos()
        Log.d("test", response.isSuccessful.toString())
        return response
    }

}