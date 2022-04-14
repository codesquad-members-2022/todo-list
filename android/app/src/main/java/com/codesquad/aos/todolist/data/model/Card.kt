package com.codesquad.aos.todolist.data.model

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("cardId")
    val cardId: Int,
    @SerializedName("content")
    val content: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("section")
    val section: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    var isSwiped: Boolean = false
)
