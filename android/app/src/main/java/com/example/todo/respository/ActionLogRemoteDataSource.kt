package com.example.todo.respository

import android.content.Context
import android.util.Log
import com.example.todo.R
import com.example.todo.common.ActionType
import com.example.todo.common.ProgressType
import com.example.todo.model.ActionLog
import com.example.todo.network.JsonActionLog
import com.example.todo.network.RetrofitClient

class ActionLogRemoteDataSource(
    private val retrofitClient: RetrofitClient,
) : ActionLogDataSource {
    override suspend fun getActionLogs(): List<JsonActionLog> {
       return retrofitClient.getActionLog().histories
    }
}