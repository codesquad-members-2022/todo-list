package com.example.todolist.tasks.data

data class Task(
    val id: Int,
    val title: String,
    val contents: String,
    val user: String,
    val status: String,
    val createdDateTime: String,
    val updatedDateTime: String
)
