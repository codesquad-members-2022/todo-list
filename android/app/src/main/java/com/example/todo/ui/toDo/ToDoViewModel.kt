package com.example.todo.ui.toDo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.model.ActionLog
import com.example.todo.model.TodoItem

class ToDoViewModel():ViewModel() {

    private val _todoList = MutableLiveData<List<TodoItem>>()
    private val _inProgressList=  MutableLiveData<List<TodoItem>>()
    private val _doneList =  MutableLiveData<List<TodoItem>>()
    private val _actionList = MutableLiveData<List<ActionLog>>()

    val todoList:LiveData<List<TodoItem>> = _todoList
    val inProgressList:LiveData<List<TodoItem>> = _inProgressList
    val doneList: LiveData<List<TodoItem>> = _doneList
    val actionList: LiveData<List<ActionLog>> = _actionList

}