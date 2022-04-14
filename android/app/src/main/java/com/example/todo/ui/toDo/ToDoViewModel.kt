package com.example.todo.ui.toDo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.ActionLog
import com.example.todo.model.ActionType
import com.example.todo.model.ProgressType
import com.example.todo.model.TodoItem
import com.example.todo.respository.ActionLogRepository
import com.example.todo.respository.ToDoRepository
import kotlinx.coroutines.launch

class ToDoViewModel(
    private val actionLogRepository: ActionLogRepository,
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    private val _todoList = MutableLiveData<List<TodoItem>>()
    private val _inProgressList = MutableLiveData<List<TodoItem>>()
    private val _doneList = MutableLiveData<List<TodoItem>>()
    private val _actionList = MutableLiveData<List<ActionLog>>()

    val todoList: LiveData<List<TodoItem>> = _todoList
    val inProgressList: LiveData<List<TodoItem>> = _inProgressList
    val doneList: LiveData<List<TodoItem>> = _doneList
    val actionList: LiveData<List<ActionLog>> = _actionList

    init {
        loadActionLog()
        loadTodoList()
        val tempTodoList = mutableListOf<TodoItem>()
        val tempInProgressList = mutableListOf<TodoItem>()
        val tempDoneList = mutableListOf<TodoItem>()

        val todo1 = TodoItem("title", "content", ProgressType.TO_DO)
        val todo2 = TodoItem("title2", "content2\ncontentcontent", ProgressType.TO_DO)
        val todo3 = TodoItem(
            "title2", "content2\ncontentcontent\n sdfsd", ProgressType.TO_DO
        )
        val todo4 = TodoItem("title", "content", ProgressType.IN_PROGRESS)
        val todo5 = TodoItem("title2", "content2\ncontentcontent", ProgressType.IN_PROGRESS)
        val todo6 = TodoItem(
            "title2", "content2\ncontentcontent\n sdfsd", ProgressType.IN_PROGRESS
        )
        val todo7 = TodoItem("title", "content", ProgressType.DONE)
        val todo8 = TodoItem("title2", "content2\ncontentcontent", ProgressType.DONE)
        val todo9 = TodoItem(
            "title2", "content2\ncontentcontent\n sdfsd", ProgressType.DONE
        )

        tempTodoList.addAll(mutableListOf(todo1, todo2, todo3))
        tempInProgressList.addAll(mutableListOf(todo4, todo5, todo6))
        tempDoneList.addAll(mutableListOf(todo8, todo9, todo7))

        _todoList.value = tempTodoList
        _inProgressList.value = tempInProgressList
        _doneList.value = tempDoneList
    }

    private fun loadTodoList() {
        viewModelScope.launch {
            val totalList = toDoRepository.getTodoItems()
            val tempTodoList = mutableListOf<TodoItem>()
            val progressList = mutableListOf<TodoItem>()
            val doneList = mutableListOf<TodoItem>()
            totalList?.forEach {
                if (it.type == ProgressType.TO_DO) tempTodoList.add(it)
                else if (it.type == ProgressType.IN_PROGRESS) progressList.add(it)
                if (it.type == ProgressType.DONE) doneList.add(it)
            }
            _todoList.value = tempTodoList
            _inProgressList.value = progressList
            _doneList.value = doneList
        }
    }

    private fun loadActionLog() {
        viewModelScope.launch {
            val actionLogs = actionLogRepository.getActionLogs()
            actionLogs?.let { _actionList.value = actionLogs }
        }
    }

    fun addTodoItem(item: TodoItem) {
        viewModelScope.launch {
            _todoList.value = todoList.value?.let { toDoRepository.addToDoItem(it, item) }
        }
    }

    fun addInProgressItem(item: TodoItem) {
        viewModelScope.launch {
            _inProgressList.value =
                inProgressList.value?.let { toDoRepository.addToDoItem(it, item) }
        }
    }

    fun addDoneItem(item: TodoItem) {
        viewModelScope.launch {
            _doneList.value = doneList.value?.let { toDoRepository.addToDoItem(it, item) }
        }
    }

    fun deleteTodoItem(item: TodoItem) {
        _todoList.value = todoList.value?.let { toDoRepository.deleteToDoItem(it, item) }
    }

    fun deleteInProgressItem(item: TodoItem) {
        _inProgressList.value =
            inProgressList.value?.let { toDoRepository.deleteToDoItem(it, item) }
    }

    fun deleteDoneItem(item: TodoItem) {
        _doneList.value = doneList.value?.let { toDoRepository.deleteToDoItem(it, item) }
    }

    fun moveToDone(item: TodoItem) {
        when (item.type) {
            ProgressType.IN_PROGRESS -> {
                deleteInProgressItem(item)
                item.type = ProgressType.DONE
                addDoneItem(item)
            }
            ProgressType.DONE -> {
                return
            }
            else -> {
                deleteTodoItem(item)
                item.type = ProgressType.DONE
                addDoneItem(item)
            }
        }

    }

    fun deleteItem(cardItem: TodoItem) {
        when (cardItem.type) {
            ProgressType.TO_DO -> {
                deleteTodoItem(cardItem)
            }
            ProgressType.IN_PROGRESS -> {
                deleteInProgressItem(cardItem)
            }
            ProgressType.DONE -> {
                deleteDoneItem(cardItem)
            }
        }
    }

    fun updateTodoItem(updateItem: TodoItem) {
        _todoList.value = todoList.value?.let { toDoRepository.updateToDoItem(it, updateItem) }
    }

    fun updateInProgressItem(updateItem: TodoItem) {
        _inProgressList.value =
            inProgressList.value?.let { toDoRepository.updateInProgressItem(it, updateItem) }
    }

    fun updateDoneItem(updateItem: TodoItem) {
        _doneList.value = doneList.value?.let { toDoRepository.updateDoneItem(it, updateItem) }
    }
}