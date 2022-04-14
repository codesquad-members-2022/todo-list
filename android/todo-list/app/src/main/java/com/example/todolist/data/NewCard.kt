package com.example.todolist.data

import com.google.gson.annotations.SerializedName

data class NewCard(
    val subject: String,
    val content: String,
    val status: String,
    val userId: Int = 1
)
