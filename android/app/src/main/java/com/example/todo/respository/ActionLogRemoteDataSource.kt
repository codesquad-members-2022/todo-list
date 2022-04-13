package com.example.todo.respository

import com.example.todo.network.ActionLogResponse
import com.example.todo.network.ActionLogResponseItem
import com.example.todo.network.RetrofitClient
import retrofit2.Response

class ActionLogRemoteDataSource(
    private val retrofitClient: RetrofitClient,
) : ActionLogDataSource {
    override suspend fun getActionLogs(): Response<ActionLogResponse> {
        return retrofitClient.getActionLog()
    }
}