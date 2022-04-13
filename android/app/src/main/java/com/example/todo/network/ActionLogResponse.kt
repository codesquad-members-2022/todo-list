package com.example.todo.network

class ActionLogResponse : ArrayList<ActionLogResponseItem>()

data class ActionLogResponseItem(
    val actionType: String,
    val cardTitle: String,
    val historyDateTime: String,
    val historyId: Int,
    val nowLocation: String,
    val pastLocation: Any
)

