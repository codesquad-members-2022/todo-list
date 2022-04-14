package com.example.todo.ui.common

import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem

interface ToDoMoveListener {

    fun moveData(rvType: ProgressType, prevItemId: Int?, nextItemId:Int?, moveItemId:Int?)

}