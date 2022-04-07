package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.model.ActionType.*
import com.example.todolist.model.History

class ViewModel : ViewModel() {

    private val _history = MutableLiveData<List<History>>()
    val history: LiveData<List<History>>
        get() = _history

    fun loadDummyData() {
        _history.value = getDummyData()
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