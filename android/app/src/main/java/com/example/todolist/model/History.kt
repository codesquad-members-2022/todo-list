package com.example.todolist.model

data class History(
    val id: Int,
    val action: String,
    val title: String,
    val nowStatus: String,
    val beforeStatus: String?,
    val createData: String,
)