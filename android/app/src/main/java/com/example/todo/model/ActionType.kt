package com.example.todo.model

import com.example.todo.R

enum class ActionType(val value: Int) {
    ADD(R.string.action_add), REMOVE((R.string.action_remove)), MOVE((R.string.action_move)), UPDATE(
        R.string.action_update
    ),
}