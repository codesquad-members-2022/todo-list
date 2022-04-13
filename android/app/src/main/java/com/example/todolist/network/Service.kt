package com.example.todolist.network

import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.Result
import com.example.todolist.model.response.TasksResponse
import retrofit2.Response
import retrofit2.http.*

interface Service {

    @Headers("Content-Type: application/json")
    @GET("cards")
    suspend fun loadTasks(): Response<TasksResponse>

    @Headers("Content-Type: application/json")
    @POST("cards")
    suspend fun saveTask(@Body cardInfo: Task): Response<Result>

    @Headers("Content-Type: application/json")
    @PATCH("cards/{id}")
    suspend fun modifyTask(
        @Path("id") id: Int,
        @Body modifyTaskRequest: ModifyTaskRequest
    ): Response<Result>
}