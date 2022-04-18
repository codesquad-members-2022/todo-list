package com.example.todolist.ui.common

enum class CardStatus(
    val status: String,
    val korStatus: String
) {
    TODO("todo", "해야할 일"), ONGOING("ongoing", "하고있는 일"), COMPLETED("completed", "완료한 일")
}