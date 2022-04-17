package com.example.todolist.network

import com.example.todolist.history.data.HistoryCard
import com.example.todolist.tasks.data.NewTask
import com.example.todolist.tasks.data.Task
import retrofit2.Response
import retrofit2.http.*

interface TodoService {

    @GET("api/{histories}")
    suspend fun getHistories(
        @Path("histories") variable: String
    ): Response<List<HistoryCard>>

    @GET("api/todos")
    suspend fun getAllTasks(): Response<List<Task>>

    @GET("api/todos/{id}")
    suspend fun getTask(
        @Path("id") id: Int
    ): Response<Task>

    @POST("api/todos")
    suspend fun createTask(
        @Body newTask: NewTask
    ): Response<Task>

    @DELETE("api/todos/{id}")
    suspend fun deleteTask(
        @Path("id") id: Int
    ): Response<Unit>

    // TODO UpdateTask 만들어야 함
    @PATCH("api/todos/{id}")
    suspend fun updateTask(
        @Path("id") id: Int,
        @FieldMap param: HashMap<String, String>
    ): Response<Task>
}