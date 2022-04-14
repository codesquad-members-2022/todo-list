package com.example.todo.ui.common

import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem

interface ToDoMoveListener {

    fun swapData(rvType: ProgressType, sourceIndex: Int, targetIndex: Int)
    fun addDataAtEnd(rvType: ProgressType, item: TodoItem)
    fun addDataAtInx(rvType: ProgressType, targetIndex: Int, item: TodoItem)
    fun deleteData(rvType: ProgressType,item: TodoItem)

}