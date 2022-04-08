package com.example.todo.respository

import com.example.todo.model.ActionLog

interface ActionLogDataSource {
    suspend fun getActionLogs(): List<ActionLog>
}