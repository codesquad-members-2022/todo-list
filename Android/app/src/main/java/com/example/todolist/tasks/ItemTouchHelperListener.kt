package com.example.todolist.tasks

interface ItemTouchHelperListener  {
    fun onItemMove(from_position: Int, to_position: Int): Boolean
    fun onItemSwipe(position: Int)
}