package com.example.todolist.model.response

data class LoadTasksResponse(
    val doneCollection: MutableList<DoneCollection>,
    val inProgressCollection: MutableList<InProgressCollection>,
    val todoCollection: MutableList<TodoCollection>
)

data class DoneCollection(
    val author: String,
    val contents: String,
    val id: Int,
    val status: String,
    val title: String
)

data class InProgressCollection(
    val author: String,
    val contents: String,
    val id: Int,
    val status: String,
    val title: String
)

data class TodoCollection(
    val author: String,
    val contents: String,
    val id: Int,
    val status: String,
    val title: String
)