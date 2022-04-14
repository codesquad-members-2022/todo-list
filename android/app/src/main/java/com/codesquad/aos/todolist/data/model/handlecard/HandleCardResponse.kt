package com.codesquad.aos.todolist.data.model.handlecard

import com.codesquad.aos.todolist.data.model.Card

data class HandleCardResponse(
    val card: Card,
    val log: HandleCardLog
)