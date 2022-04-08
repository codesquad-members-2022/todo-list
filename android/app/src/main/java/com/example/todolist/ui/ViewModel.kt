package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.model.ActionType.*
import com.example.todolist.model.History
import com.example.todolist.model.Task

class ViewModel : ViewModel() {

    private val _history = MutableLiveData<List<History>>()
    val history: LiveData<List<History>>
        get() = _history

    private val todoItems: MutableList<Task> = mutableListOf()
    private var _todoTask = MutableLiveData<MutableList<Task>>()
    val todoTask: LiveData<MutableList<Task>>
        get() = _todoTask

    fun loadDummyData() {
        _history.value = getDummyData()
    }

    fun addTask(task: Task) {
        todoItems.add(task)
        _todoTask.value = todoItems
    }

    private fun getDummyData(): List<History> {
        return listOf(
            History(1, MOVE, "HTML/CSS공부하기", "해야할 일", "하고 있는 일", "2022-04-05 21:19:00"),
            History(2, ADD, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
            History(3, REMOVE, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
            History(4, UPDATE, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
        )
    }

}