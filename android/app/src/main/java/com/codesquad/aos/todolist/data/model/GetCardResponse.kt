package com.codesquad.aos.todolist.data.model

data class GetCardResponse(
    val classifiedCardsDTO: ClassifiedCardsDTO,
    val logs: List<LogX>
)