package com.example.todolist.data

data class Task(
    val id: String,
    val status: Int,
    val title: String,
    val body: String,
    val order: Int
)
