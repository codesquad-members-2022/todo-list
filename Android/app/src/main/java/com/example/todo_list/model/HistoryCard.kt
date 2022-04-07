package com.example.todo_list.model

import com.google.gson.annotations.SerializedName

data class HistoryCard(
    val id: Int,
    val todo: todo,

    val action: String,
    @SerializedName("from status")
    val from_status: String,
    @SerializedName("to status")
    val to_status: String,
    val createdDateTime: String
)

data class todo(
    val id: Int,
    val title: String,
    val contents: String,
    val user: String,
    val status: String,
    val createdDateTime: String,
    val updatedDateTime: String
)

data class Todos(
    val id: Int,
    val title: String,
    val contents: String,
    val user: String,
    val status: String,
    val createdDateTime: String,
    val updatedDateTime: String
)