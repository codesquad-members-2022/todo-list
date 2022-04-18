package com.example.todolist.data

data class NewCard(
    val subject: String,
    val content: String,
    val status: String,
    val userId: Int = 1
)
