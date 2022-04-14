package com.codesquad.aos.todolist.data.model.handlecard

import com.google.gson.annotations.SerializedName

data class AddCard(
    @SerializedName("content")
    val content: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("section")
    val section: String,
    @SerializedName("title")
    val title: String
)
