package com.codesquad.aos.todolist.data.model.handlecard

import com.google.gson.annotations.SerializedName

data class EditCard(
    @SerializedName("content")
    val content: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("section")
    val section: String,
    @SerializedName("title")
    val title: String
)
