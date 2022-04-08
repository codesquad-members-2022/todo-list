package com.example.todo.ui.toDo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.model.ActionLog
import com.example.todo.model.TodoItem
import com.example.todo.respository.ActionLogRepository
import kotlinx.coroutines.launch

class ToDoViewModel(private val actionLogRepository: ActionLogRepository) : ViewModel() {

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
    }

    private fun loadActionLog() {
        viewModelScope.launch {
            val actionLogs = actionLogRepository.getActionLogs()
            _actionList.value = actionLogs
            Log.d("ssss", actionLogs.toString())
        }
    }
}