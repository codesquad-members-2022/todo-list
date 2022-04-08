package com.example.todolist.model

data class TasksResponse(
    val todo: MutableList<TaskResponse>,
    val inProgress: MutableList<TaskResponse>,
    val done: MutableList<TaskResponse>,
)

data class TaskResponse(
    val id: Int,
    val title: String?,
    val content: String?,
    val status: Status,
    val author: String = "Android",
)