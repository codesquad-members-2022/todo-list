package com.example.todo.respository

import android.content.Context
import android.util.Log
import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem
import com.example.todo.network.Todo
import com.example.todo.network.TodoResponseItem
import com.example.todo.network.UpdateTodoBody
import retrofit2.Response

class ToDoRepository(private val context: Context, private val toDoDataSource: ToDoDataSource) {


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

    private fun jsonInProgressListToTodoItems(list: List<Todo>): List<TodoItem> {
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

    private fun jsonDoneToTodoItems(list: List<Todo>): List<TodoItem> {
        return list.map { TodoItem(it.title, it.content, ProgressType.DONE, it.id, it.nextId) }
    }

    suspend fun addToDoItem(toDoList: List<TodoItem>, newItem: TodoItem): List<TodoItem> {
        val response = when (newItem.type) {
            ProgressType.TO_DO -> toDoDataSource.getTodoId(newItem)
            ProgressType.IN_PROGRESS -> toDoDataSource.getInProgressId(newItem)
            ProgressType.DONE -> toDoDataSource.getDoneId(newItem)
        }
        Log.d("testApi", response.isSuccessful.toString())
        return if (response.isSuccessful) {
            val originList = toDoList.toMutableList()
            newItem.itemId = response?.body()?.cardId ?: -1
            if (originList.size != 0) originList[originList.size - 1].next = newItem.itemId
            originList.add(0, newItem)
            originList.toList()
        } else toDoList
    }

    suspend fun removeToDoItem(toDoList: List<TodoItem>, deleteItem: TodoItem): List<TodoItem> {
        val originList = toDoList.toMutableList()
        deleteItem.itemId?.let {
            val response = toDoDataSource.removeItem(it.toInt())
            if (response.isSuccessful) {
                // 새로운 next 설정
                val preItem = toDoList.find { item -> item.itemId == deleteItem.itemId }
                preItem?.itemId = deleteItem.next
                Log.d("xx", preItem?.itemId.toString())
                originList.remove(deleteItem)
            }
            return originList.toList()
        } ?: kotlin.run { return originList.toList() }
    }

    suspend fun updateToDoItem(
        todoList: List<TodoItem>,
        updateItem: TodoItem
    ): List<TodoItem> {
        val originList = todoList.toMutableList()
        val response = updateItem.itemId?.let {
            toDoDataSource.updateItem(it, UpdateTodoBody(updateItem.title, updateItem.content))
        }
        return if (response?.isSuccessful == true) {
            val originItemIndex =
                originList.indexOf(originList.find { it.itemId == updateItem.itemId })
            // 실제 변경
            originList[originItemIndex] = updateItem
            Log.d("test", originList[originItemIndex].title)
            originList.toList()
        } else todoList

    }

    suspend fun moveTodoItem(
        itemId: Int,
        targetProgressType: ProgressType,
        prevId: Int?,
        nextId: Int?
    ): Boolean {
        val type = context.resources.getString(targetProgressType.value)
        val response = toDoDataSource.moveItem(itemId, type, prevId, nextId)
        return response.isSuccessful
    }


}