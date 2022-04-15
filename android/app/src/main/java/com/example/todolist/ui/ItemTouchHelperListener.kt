package com.example.todolist.ui

import com.example.todolist.model.response.TaskDetailResponse

interface ItemTouchHelperListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemSwipe(position: Int)

    fun add(type: Int, index: Int, task: TaskDetailResponse)

    fun remove(index: Int, task: TaskDetailResponse)
}