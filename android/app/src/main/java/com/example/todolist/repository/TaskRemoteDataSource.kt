package com.example.todolist.repository

import com.example.todolist.model.History
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.CommonResponse
import com.example.todolist.model.response.TasksResponse
import com.example.todolist.network.RetrofitAPI

class TaskRemoteDataSource : TaskDataSource {

    override suspend fun loadTasks(): TasksResponse? {
        val response = RetrofitAPI.service.loadTasks()
        return if (response.isSuccessful) response.body() else null
    }

    override suspend fun addTask(cardData: Task): CommonResponse? {
        val response = RetrofitAPI.service.saveTask(cardData)
        return if (response.isSuccessful) response.body() else null
    }

    override suspend fun modifyTask(modifyTaskRequest: ModifyTaskRequest): CommonResponse? {
        val response = RetrofitAPI.service.modifyTask(modifyTaskRequest.id, modifyTaskRequest)
        return if (response.isSuccessful) response.body() else null
    }

    override suspend fun loadHistory(): List<History>? {
        val response = RetrofitAPI.service.loadHistory()
        return if (response.isSuccessful) response.body() else null
    }
}