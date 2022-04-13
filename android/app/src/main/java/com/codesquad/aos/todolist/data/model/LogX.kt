package com.codesquad.aos.todolist.data.model

data class LogX(
    val logEventType: String,
    val logTime: String,
    val prevSection: Any,
    val section: String,
    val title: String
)