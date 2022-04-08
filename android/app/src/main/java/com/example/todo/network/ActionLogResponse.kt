package com.example.todo.network

data class JsonActionLog(
    val historyId: Int,
    val actionType: String,
    val cardTitle: String,
    val pastLocation: String,
    val nowLocation: String,
    val historyDate: String
)

data class ActionLogResponse(
    val histories: List<JsonActionLog>
)
