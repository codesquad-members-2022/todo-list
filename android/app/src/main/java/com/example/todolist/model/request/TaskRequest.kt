package com.example.todolist.model.request

data class TaskRequest(
    val title: String,
    val contents: String,
    val status: String,
    val author: String
)
