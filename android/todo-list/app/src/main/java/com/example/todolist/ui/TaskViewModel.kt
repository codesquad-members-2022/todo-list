package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Task
import com.example.todolist.data.TodoRepository

class TaskViewModel : ViewModel() {

    private val todoRepository = TodoRepository()

    private val _todoTaskList = MutableLiveData<List<Task>>()
    val todoTaskList: LiveData<List<Task>> = _todoTaskList

    private val _onGoingTaskList = MutableLiveData<List<Task>>()
    val onGoingTaskList: LiveData<List<Task>> = _onGoingTaskList

    private val _completeTaskList = MutableLiveData<List<Task>>()
    val completeTaskList: LiveData<List<Task>> = _completeTaskList

    private var _state = 0
    val state: Int
        get() = _state

    init {
        loadAllTask()
    }

    private fun loadAllTask() {
        loadTodoTask()
        loadOngoingTask()
        loadCompletedTask()
    }

    private fun loadCompletedTask() {
        _completeTaskList.value = todoRepository.getCompleteTaskList()
    }

    private fun loadTodoTask() {
        _todoTaskList.value = todoRepository.getTaskList()
    }

    private fun loadOngoingTask() {
        _onGoingTaskList.value = todoRepository.getOnGoingTaskList()
    }

    fun addTodoCard(title: String, content: String) {
        todoRepository.testAdd(title, content)
        loadTodoTask()
        _state = 1
    }

    fun addOnGoingCard(title: String, content: String) {
        todoRepository.testOnGoingAdd(title, content)
        loadOngoingTask()
        _state = 1
    }

    fun addCompletedCard(title: String, content: String) {
        todoRepository.testCompletedAdd(title, content)
        loadCompletedTask()
        _state = 1
    }

    fun deleteTodoCard(task: Task) {
        todoRepository.deleteTodoTask(task)
        loadTodoTask()
        _state = 2
    }

    fun deleteOngoingCard(task: Task) {
        todoRepository.deleteOnGoingTask(task)
        loadOngoingTask()
        _state = 2
    }

    fun deleteCompletedCard(task: Task) {
        todoRepository.deleteCompletedTask(task)
        loadCompletedTask()
        _state = 2
    }

    fun dropTodoCard(droppedTask: Task, targetedTask: Task) {
        todoRepository.dropTodoTask(droppedTask, targetedTask)
        loadTodoTask()
        _state = 3
    }

    fun dropOngoingCard(droppedTask: Task, targetedTask: Task) {
        todoRepository.dropOngoingTask(droppedTask, targetedTask)
        loadOngoingTask()
        _state = 3
    }

    fun dropCompletedCard(droppedTask: Task, targetedTask: Task) {
        todoRepository.dropCompletedTask(droppedTask, targetedTask)
        loadCompletedTask()
        _state = 3
    }


}