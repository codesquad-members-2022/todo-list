package com.example.todo.model


data class ActionLog(
    val title: String,
    val actionType: ActionType,
    val time: String,
    val nowProgressType: ProgressType,
    val prevProgressType: ProgressType?
) {
}
