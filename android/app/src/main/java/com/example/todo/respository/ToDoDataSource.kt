package com.example.todo.respository

import com.example.todo.model.TodoItem

interface ToDoDataSource {
    fun getToDoItems(): List<TodoItem>
}