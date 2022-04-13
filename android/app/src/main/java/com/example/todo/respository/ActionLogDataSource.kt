package com.example.todo.respository

import com.example.todo.network.ActionLogResponse
import com.example.todo.network.ActionLogResponseItem
import retrofit2.Response

interface ActionLogDataSource {
    suspend fun getActionLogs(): Response<ActionLogResponse>
}