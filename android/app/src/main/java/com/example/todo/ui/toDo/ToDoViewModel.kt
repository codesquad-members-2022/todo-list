package com.example.todo.ui.toDo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.ActionLog
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

    var todoList: LiveData<List<TodoItem>> = _todoList
    var inProgressList: LiveData<List<TodoItem>> = _inProgressList
    var doneList: LiveData<List<TodoItem>> = _doneList
    val actionList: LiveData<List<ActionLog>> = _actionList

    init {
        loadActionLog()
        val tempTodoList = mutableListOf<TodoItem>()
        val tempInProgressList = mutableListOf<TodoItem>()
        val tempDoneList = mutableListOf<TodoItem>()

        val todo1 = TodoItem("title", "content", ProgressType.TO_DO)
        val todo2 = TodoItem("title2", "content2\ncontentcontent", ProgressType.TO_DO)
        val todo3 = TodoItem(
            "title2", "content2\ncontentcontent\n sdfsd", ProgressType.TO_DO
        )

        tempTodoList.addAll(mutableListOf(todo1, todo2, todo3))
        tempInProgressList.addAll(mutableListOf(todo2, todo3, todo1))
        tempDoneList.addAll(mutableListOf(todo1, todo2, todo3))

        _todoList.value = tempTodoList
        _inProgressList.value = tempInProgressList
        _doneList.value = tempDoneList
    }

    private fun loadActionLog() {
        viewModelScope.launch {
            val actionLogs = actionLogRepository.getActionLogs()
            _actionList.value = actionLogs
            Log.d("ssss", actionLogs.toString())
        }
    }

    fun addTodoItem(item: TodoItem) {
        viewModelScope.launch {
            _todoList.value = todoList.value?.let { toDoRepository.addToDoItem(it, item) }
            todoList = _todoList
        }
    }

//    fun addInProgressItem(item: TodoItem) {
//        _inProgressList.value = inProgressList.value?.let {
//            val originList = mutableListOf<TodoItem>()
//            originList.addAll(it)
//            originList.add(0, item)
//            originList.toList()
//        }
//        inProgressList = _inProgressList
//    }
    fun addInProgressItem(item: TodoItem) {
        viewModelScope.launch {
            _inProgressList.value =
                inProgressList.value?.let { toDoRepository.addInProgressItem(it, item) }
            inProgressList = _inProgressList
        }
    }

    fun addDoneItem(item: TodoItem) {
        viewModelScope.launch {
            _doneList.value = doneList.value?.let { toDoRepository.addDoneItem(it, item) }
            doneList = _doneList
        }
    }
}