package com.example.todolist.data

import java.io.Serializable

data class Task(
    val userId: String,
    val id: Int,
    val status: String,
    val title: String,
    val body: String,
    var order: Int
) : Serializable
