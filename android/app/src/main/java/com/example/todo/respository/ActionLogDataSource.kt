package com.example.todo.respository

import com.example.todo.network.JsonActionLog

interface ActionLogDataSource {
    suspend fun getActionLogs(): List<JsonActionLog>
}