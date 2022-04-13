package com.example.todo_list.tasks

interface ItemTouchHelperListener  {
    fun onItemMove(from_position: Int, to_position: Int): Boolean
    fun onItemSwipe(position: Int)
}