package com.example.todo.common

import com.example.todo.R

enum class ProgressType(val value: Int) {
    TO_DO(R.string.progress_todo), IN_PROGRESS(R.string.progress_ing), DONE(R.string.progress_done)
}

enum class ActionType(val value: Int) {
    ADD(R.string.action_add), REMOVE((R.string.action_remove)), MOVE((R.string.action_move)), UPDATE(
        R.string.action_update
    ),
}