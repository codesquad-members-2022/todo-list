package com.codesquad.aos.todolist.data.model

data class CardData(
    val cardId: Int,
    val content: String,
    val order: Int,
    val section: String,
    val title: String
)
