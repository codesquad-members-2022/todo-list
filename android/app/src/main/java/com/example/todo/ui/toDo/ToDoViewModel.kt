package com.example.todo.ui.toDo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.ActionLog
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

    var todoList: LiveData<List<TodoItem>> = _todoList
    var inProgressList: LiveData<List<TodoItem>> = _inProgressList
    var doneList: LiveData<List<TodoItem>> = _doneList
    val actionList: LiveData<List<ActionLog>> = _actionList

    init {
        loadActionLog()
    }

    private fun loadActionLog() {
        viewModelScope.launch {
            val actionLogs = actionLogRepository.getActionLogs()
            _actionList.value = actionLogs
            Log.d("ssss", actionLogs.toString())
        }
    }

    fun addTodoItem(item: TodoItem) {
        _todoList.value = todoList.value?.let { toDoRepository.addToDoItem(it, item) }
        todoList = _todoList
    }

    fun addInProgressItem(item: TodoItem) {
        _inProgressList.value =
            inProgressList.value?.let { toDoRepository.addInProgressItem(it, item) }
        inProgressList = _inProgressList
    }

    fun addDoneItem(item: TodoItem) {
        _doneList.value = doneList.value?.let { toDoRepository.addDoneItem(it, item) }
        doneList = _doneList
    }
}