package com.example.todolist.model.request

data class ModifyTaskRequest(
    val id: Int,
    val title: String,
    val contents: String,
    val author: String = "Android",
    val status: String
)
