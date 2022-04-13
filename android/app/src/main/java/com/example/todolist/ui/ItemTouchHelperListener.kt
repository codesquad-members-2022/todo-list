package com.example.todolist.ui

interface ItemTouchHelperListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemSwipe(position: Int)
}