package com.example.todolist.network

import com.example.todolist.model.History
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.CommonResponse
import com.example.todolist.model.response.TasksResponse
import retrofit2.Response
import retrofit2.http.*

interface Service {

    @Headers("Content-Type: application/json")
    @GET("cards")
    suspend fun loadTasks(): Response<TasksResponse>

    @Headers("Content-Type: application/json")
    @POST("cards")
    suspend fun saveTask(@Body cardInfo: Task): Response<CommonResponse>

    @Headers("Content-Type: application/json")
    @PATCH("cards/{id}")
    suspend fun modifyTask(
        @Path("id") id: Int,
        @Body modifyTaskRequest: ModifyTaskRequest
    ): Response<CommonResponse>

    @Headers("Content-Type: application/json")
    @GET("activity-logs")
    suspend fun loadHistory(): Response<List<History>>

    @DELETE("cards/{id}")
    suspend fun deleteTask(@Path("id") id: Int): Response<CommonResponse>

}