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

    fun deleteToDoItem(toDoList: List<TodoItem>, deleteItem: TodoItem): List<TodoItem> {
        val originList = toDoList.toMutableList()
        originList.remove(deleteItem)
        return originList.toList()
    }

    fun deleteInProgressItem(inProgressList: List<TodoItem>, deleteItem: TodoItem): List<TodoItem> {
        val originList = inProgressList.toMutableList()
        originList.remove(deleteItem)
        return originList.toList()
    }

    fun deleteDoneItem(doneList: List<TodoItem>, deleteItem: TodoItem): List<TodoItem> {
        val originList = doneList.toMutableList()
        originList.remove(deleteItem)
        return originList.toList()
    }

    fun updateToDoItem(toDoList: List<TodoItem>, updateItem: TodoItem): List<TodoItem> {
        val originList = toDoList.toMutableList()
        val originItemIndex= originList.indexOf(originList.find { it.itemId== updateItem.itemId })
        originList[originItemIndex]= updateItem
        return originList.toList()

    }

    fun updateInProgressItem(inProgressList: List<TodoItem>, updateItem: TodoItem):List<TodoItem>{
        val originList = inProgressList.toMutableList()
        val originItemIndex= originList.indexOf(originList.find { it.itemId== updateItem.itemId })
        originList[originItemIndex]= updateItem
        return originList.toList()
    }

    fun updateDoneItem(doneList: List<TodoItem>, updateItem: TodoItem):List<TodoItem>{
        val originList = doneList.toMutableList()
        val originItemIndex= originList.indexOf(originList.find { it.itemId== updateItem.itemId })
        originList[originItemIndex]= updateItem
        return originList.toList()
    }

}