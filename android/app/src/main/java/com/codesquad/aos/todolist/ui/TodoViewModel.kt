package com.codesquad.aos.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.Log

class TodoViewModel: ViewModel() {

    private val _todoListId = MutableLiveData<MutableList<Card>>()
    private var todolist = mutableListOf<Card>()
    val todoListId: LiveData<MutableList<Card>> get() = _todoListId

    private val _progressListId = MutableLiveData<MutableList<Card>>()
    private var progresslist = mutableListOf<Card>()
    val progressListId: LiveData<MutableList<Card>> get() = _progressListId

    private val _completeListId = MutableLiveData<MutableList<Card>>()
    private var completelist = mutableListOf<Card>()
    val completeListId: LiveData<MutableList<Card>> get() = _completeListId

    private val _LogListId = MutableLiveData<MutableList<Log>>()
    private var LogList = mutableListOf<Log>()
    val LogListId: LiveData<MutableList<Log>> get() = _LogListId

    // 해야할 일 추가
    fun addTodo(title: String, content: String){
        todolist.add(0, Card((todolist.size + 1), title, content, "author by android"))
        _todoListId.value = todolist
    }

    fun addProgress(title: String, content: String){
        progresslist.add(0, Card((progresslist.size + 1), title, content, "author by android"))
        _progressListId.value = progresslist
    }

    fun addComplete(title: String, content: String){
        completelist.add(0, Card((completelist.size + 1), title, content, "author by android"))
        _completeListId.value = completelist
    }

    fun addLog(log: String, time: String){
        LogList.add(Log("@Han", log, time))
        _LogListId.value = LogList
    }

}