package com.example.todolist.network

import com.example.todolist.model.Task
import com.example.todolist.model.response.AddTasksResponse
import com.example.todolist.model.response.TasksResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface Service {

    @Headers("Content-Type: application/json")
    @GET("cards")
    suspend fun loadTasks(): Response<TasksResponse>

    @Headers("Content-Type: application/json")
    @POST("cards")
    suspend fun saveTask(@Body cardInfo: Task): Response<AddTasksResponse>
}