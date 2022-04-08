package com.example.todo.network

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

data class JsonTodo(
    @SerializedName("cardId")
    val itemId: String,
    val title: String,
    val content: String,
    val writer: String,
    val nextId: String,
    val uploadDate: String
)