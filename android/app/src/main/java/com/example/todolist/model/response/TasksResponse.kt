package com.example.todolist.model

data class TasksResponse(
    val todo: MutableList<TaskDetailResponse>,
    val inProgress: MutableList<TaskDetailResponse>,
    val done: MutableList<TaskDetailResponse>,
)

data class TaskDetailResponse(
    val id: Int,
    val title: String,
    val content: String,
    var status: Status,
    val author: String = "Android",
)