package com.codesquad.aos.todolist.data.model.handlecard

import com.codesquad.aos.todolist.data.model.Card

data class HandleCardResponse(
    val author: String,
    val card: Card,
    val log: AddCardLog
)