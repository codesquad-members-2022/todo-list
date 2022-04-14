package com.codesquad.aos.todolist.data.model

data class Todo(
    val cardId: Int,
    val content: String,
    val order: Int,
    val section: String,
    val title: String
)