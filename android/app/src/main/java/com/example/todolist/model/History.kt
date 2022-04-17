package com.example.todolist.model

data class History(
    val id: Int,
    val action: ActionType,
    val title: String,
    val nowStatus: String,
    val beforeStatus: String?,
    val createDateTime: String,
)

enum class ActionType(val action: String) {
    ADD("add"),
    REMOVE("remove"),
    UPDATE("update"),
    MOVE("move")
}