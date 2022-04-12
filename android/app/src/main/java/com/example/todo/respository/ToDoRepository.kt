package com.example.todo.respository

import android.util.Log
import com.example.todo.model.TodoItem

class ToDoRepository(private val toDoDataSource: ToDoDataSource) {

    suspend fun addToDoItem(toDoList: List<TodoItem>, newItem: TodoItem): List<TodoItem> {
        val response = toDoDataSource.getTodoId(newItem)
        return if (response.isSuccessful) {
            val originList = toDoList.toMutableList()
            newItem.itemId = response?.body()?.card_id ?: -1
            originList[1].next = newItem
            originList.add(0, newItem)
            originList.toList()
        } else toDoList
    }

    suspend fun addInProgressItem(
        inProgressList: List<TodoItem>,
        newItem: TodoItem
    ): List<TodoItem> {
        val response = toDoDataSource.getInProgressId(newItem)
        return if (response.isSuccessful) {
            val originList = inProgressList.toMutableList()
            newItem.itemId = response?.body()?.card_id ?: -1
            originList[1].next = newItem
            originList.add(0, newItem)
            originList.toList()
        } else inProgressList
    }

    suspend fun addDoneItem(doneList: List<TodoItem>, newItem: TodoItem): List<TodoItem> {
        val response = toDoDataSource.getDoneId(newItem)
        //Log.d("test", response.isSuccessful.toString())
        return if (response.isSuccessful) {

            val originList = doneList.toMutableList()
            newItem.itemId = response?.body()?.card_id ?: -1
            originList[1].next = newItem
            originList.add(0, newItem)
            originList.toList()
        } else doneList
    }

}