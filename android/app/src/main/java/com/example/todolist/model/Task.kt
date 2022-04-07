package com.example.todolist.model

data class Task(
    val title: String?,
    val content: String?,
    val status: Status,
    val author: String = "Android"
)
