package com.example.todolist.data

data class Task(
    val userId: String,
    val id: Int,
    val status: String,
    val title: String,
    val body: String,
    val order: Int
)
