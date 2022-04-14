package com.example.todo.network

data class AddPostBody(
    val title: String,
    val content: String,
    val currentLocation: String,
    val writer: String
)