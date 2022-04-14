package com.codesquad.aos.todolist.data.model

data class Card(
    val cardId: Int,
    val content: String,
    val order: Int,
    val section: String,
    val title: String
)
