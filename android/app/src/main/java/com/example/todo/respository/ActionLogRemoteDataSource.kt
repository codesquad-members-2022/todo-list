package com.example.todo.respository

import com.example.todo.network.JsonActionLog
import com.example.todo.network.RetrofitClient

class ActionLogRemoteDataSource(
    private val retrofitClient: RetrofitClient,
) : ActionLogDataSource {
    override suspend fun getActionLogs(): List<JsonActionLog> {
       return retrofitClient.getActionLog().histories
    }
}