package com.example.todo.network

data class MoveTodoBody(
    val destinationLocation: String,
    val nextItemId: Int?,
    val prevItemId: Int?
)
