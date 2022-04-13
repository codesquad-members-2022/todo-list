package com.example.todo.respository

import android.util.Log
import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem
import com.example.todo.network.Done
import com.example.todo.network.Ing
import com.example.todo.network.Todo
import com.example.todo.network.TodoResponseItem

class ToDoRepository(private val toDoDataSource: ToDoDataSource) {

    suspend fun getTodoItems(): List<TodoItem>? {
        val response = toDoDataSource.getTodoItems()
        Log.d("test", response.isSuccessful.toString())

        return if (response.isSuccessful) {
            val resultList = mutableListOf<TodoItem>()
            val todoJsonList = response.body()?.cardsClassifiedByLocation?.todo
            val ingJsonList = response.body()?.cardsClassifiedByLocation?.ing
            val doneJsonList = response.body()?.cardsClassifiedByLocation?.done
            todoJsonList?.let { jsonTodoListToTodoItems(it) }?.let { resultList.addAll(it) }
            ingJsonList?.let { jsonInProgressListToTodoItems(it) }?.let { resultList.addAll(it) }
            doneJsonList?.let { jsonDoneToTodoItems(it) }?.let { resultList.addAll(it) }
            resultList
        } else null
        return null
    }

    private fun jsonTodoListToTodoItems(list: List<Todo>): List<TodoItem> {
        return list.map { TodoItem(it.title, it.content, ProgressType.TO_DO, it.id, it.nextId) }
    }

    private fun jsonInProgressListToTodoItems(list: List<Ing>): List<TodoItem> {
        return list.map {
            TodoItem(
                it.title,
                it.content,
                ProgressType.IN_PROGRESS,
                it.id,
                it.nextId
            )
        }
    }

    private fun jsonDoneToTodoItems(list: List<Done>): List<TodoItem> {
        return list.map { TodoItem(it.title, it.content, ProgressType.DONE, it.id, it.nextId) }
    }

    suspend fun addToDoItem(toDoList: List<TodoItem>, newItem: TodoItem): List<TodoItem> {
        val response = toDoDataSource.getTodoId(newItem)
        return if (response.isSuccessful) {
            val originList = toDoList.toMutableList()
            newItem.itemId = response?.body()?.card_id ?: -1
            originList[originList.size].next = newItem.itemId
            originList.add(0, newItem)
            originList.toList()
        } else toDoList
    }

    fun deleteToDoItem(toDoList: List<TodoItem>, deleteItem: TodoItem): List<TodoItem> {
        val originList = toDoList.toMutableList()
        originList.remove(deleteItem)
        return originList.toList()
    }

    fun updateToDoItem(toDoList: List<TodoItem>, updateItem: TodoItem): List<TodoItem> {
        val originList = toDoList.toMutableList()
        val originItemIndex = originList.indexOf(originList.find { it.itemId == updateItem.itemId })
        originList[originItemIndex] = updateItem
        return originList.toList()

    }

    fun updateInProgressItem(inProgressList: List<TodoItem>, updateItem: TodoItem): List<TodoItem> {
        val originList = inProgressList.toMutableList()
        val originItemIndex = originList.indexOf(originList.find { it.itemId == updateItem.itemId })
        originList[originItemIndex] = updateItem
        return originList.toList()
    }

    fun updateDoneItem(doneList: List<TodoItem>, updateItem: TodoItem): List<TodoItem> {
        val originList = doneList.toMutableList()
        val originItemIndex = originList.indexOf(originList.find { it.itemId == updateItem.itemId })
        originList[originItemIndex] = updateItem
        return originList.toList()
    }

}