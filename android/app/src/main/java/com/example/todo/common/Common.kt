package com.example.todo.common

import com.example.todo.R

enum class ProgressType(val value: Int) {
    TO_DO(R.string.progress_todo), IN_PROGRESS(R.string.progress_ing), DONE(R.string.progress_done)
}