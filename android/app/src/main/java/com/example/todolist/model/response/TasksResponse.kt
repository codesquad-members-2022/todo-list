package com.example.todolist.model.response

import com.example.todolist.model.Status
import com.google.gson.annotations.SerializedName

data class TasksResponse(
    @SerializedName("todoCollection")
    val todo: MutableList<TaskDetailResponse>,
    @SerializedName("inProgressCollection")
    val inProgress: MutableList<TaskDetailResponse>,
    @SerializedName("doneCollection")
    val done: MutableList<TaskDetailResponse>,
)

data class Result(
    val status: Int,
    val taskDetailResponse: TaskDetailResponse
)
data class TaskDetailResponse(
    val id: Int,
    val title: String,
    val content: String,
    val status: Status,
    val author: String = "Android",
    val updateDateTime: String?
)