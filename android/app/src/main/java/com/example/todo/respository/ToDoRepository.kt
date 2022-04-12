package com.example.todo.respository

import com.example.todo.model.TodoItem

class ToDoRepository(private val toDoDataSource: ToDoDataSource) {

    fun addToDoItem(toDoList: List<TodoItem>, newItem: TodoItem): List<TodoItem> {
        val originList = toDoList.toMutableList()
        originList.add(0, newItem)
        return originList.toList()
    }

    fun addInProgressItem(inProgressList: List<TodoItem>, newItem: TodoItem): List<TodoItem> {
        val originList = inProgressList.toMutableList()
        originList.add(0, newItem)
        return originList.toList()
    }

    fun addDoneItem(doneList: List<TodoItem>, newItem: TodoItem): List<TodoItem> {
        val originList = doneList.toMutableList()
        originList.add(0, newItem)
        return originList.toList()
    }

}