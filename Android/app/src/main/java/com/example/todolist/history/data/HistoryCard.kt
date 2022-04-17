package com.example.todolist.history.data

data class HistoryCard(
    val id: Int,
    val todoId: Int,
    val todoTitle: String,
    val user: String,
    val action: String,
    val fromStatus: String,
    val toStatus: String,
    val createdAt: String
)
