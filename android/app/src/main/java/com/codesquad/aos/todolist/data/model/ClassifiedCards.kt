package com.codesquad.aos.todolist.data.model

data class ClassifiedCards(
    val doing: List<Card>,
    val done: List<Card>,
    val todo: List<Card>
)