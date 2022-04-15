package com.example.todolist.model.request

import com.example.todolist.model.Status

data class MoveTaskRequest(
    val beforeStatus: Status,
    val nowStatus: Status
)
