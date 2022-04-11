package com.example.todolist.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Task
import com.example.todolist.data.TodoRepository

class TaskViewModel : ViewModel() {

    private val repository = TodoRepository()

    private val _todoTaskList = MutableLiveData<List<Task>>()
    val todoTaskList: LiveData<List<Task>> = _todoTaskList

    private val _onGoingTaskList = MutableLiveData<List<Task>>()
    val onGoingTaskList: LiveData<List<Task>> = _todoTaskList

    private val _completeTaskList = MutableLiveData<List<Task>>()
    val completeTaskList: LiveData<List<Task>> = _todoTaskList

    private var _state = 0
    val state: Int
        get() = _state

    fun loadTodoTask() {
        _todoTaskList.value = repository.getTaskList()
    }

    fun addTodo(title: String, content: String) {
        Log.d("AppTest", "add todo")
        repository.testAdd(title, content)
        loadTodoTask()
        _state = 1
    }

    fun deleteTodoCard(task: Task) {
        repository.deleteTask(task)
        loadTodoTask()
        _state = 0
    }

}