package com.example.todo.respository

import com.example.todo.model.ActionLog

class ActionLogRepository(private val actionLogDataSource: ActionLogDataSource) {
    suspend fun getActionLogs(): List<ActionLog> {
        return actionLogDataSource.getActionLogs()
    }
}