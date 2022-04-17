package com.codesquad.aos.todolist.data.model.handlecard

import com.google.gson.annotations.SerializedName

data class MoveCard(
    @SerializedName("nextOrder")
    val nextOrder: Int,
    @SerializedName("preOrder")
    val preOrder: Int,
    @SerializedName("prevSection")
    val prevSection: String,
    @SerializedName("section")
    val section: String
)
