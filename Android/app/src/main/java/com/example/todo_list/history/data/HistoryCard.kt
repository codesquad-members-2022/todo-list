package com.example.todo_list.history.data

import com.google.gson.annotations.SerializedName

data class HistoryCard(
    val id: Int,
    val todo: Todo,

    val action: String,
    @SerializedName("from status")
    val from_status: String,
    @SerializedName("to status")
    val to_status: String,
    val createdDateTime: String
)

data class Todo(
    val id: Int,
    val title: String,
    val contents: String,
    val user: String,
    val status: String,
    val createdDateTime: String,
    val updatedDateTime: String
)
