package com.codesquad.aos.todolist.data.model.handlecard

data class HandleCardLog(
    val logEventType: String,
    val logTime: String,
    val prevSection: Any,
    val section: String,
    val title: String
)
