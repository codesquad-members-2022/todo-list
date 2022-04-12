package com.codesquad.aos.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.Log

class TodoViewModel: ViewModel() {

    private val _todoListLd = MutableLiveData<MutableList<Card>>()
    private var todolist = mutableListOf<Card>()
    val todoListLd: LiveData<MutableList<Card>> get() = _todoListLd

    private val _progressListLd = MutableLiveData<MutableList<Card>>()
    private var progresslist = mutableListOf<Card>()
    val progressListLd: LiveData<MutableList<Card>> get() = _progressListLd

    private val _completeListLd = MutableLiveData<MutableList<Card>>()
    private var completelist = mutableListOf<Card>()
    val completeListLd: LiveData<MutableList<Card>> get() = _completeListLd

    private val _LogListLd = MutableLiveData<MutableList<Log>>()
    private var LogList = mutableListOf<Log>()
    val LogListLd: LiveData<MutableList<Log>> get() = _LogListLd

    private var todoId = 1
    private var progressId = 1
    private var completeId = 1

    // 해야할 일 추가
    fun addTodo(title: String, content: String){
        todolist.add(0, Card(todoId++, title, content, "author by android"))
        _todoListLd.value = todolist
    }

    fun addProgress(title: String, content: String){
        progresslist.add(0, Card(progressId++, title, content, "author by android"))
        _progressListLd.value = progresslist
    }

    fun addComplete(title: String, content: String){
        completelist.add(0, Card(completeId++, title, content, "author by android"))
        _completeListLd.value = completelist
    }

    fun addLog(log: String, time: String){
        LogList.add(Log("@Han", log, time))
        _LogListLd.value = LogList
    }

    // 삭제
    fun deleteTodo(inx: Int){
        todolist.removeAt(inx)
        _todoListLd.value = todolist
    }

    fun deleteProgress(inx: Int){
        progresslist.removeAt(inx)
        _progressListLd.value = progresslist
    }

    fun deleteComplete(inx: Int) {
        completelist.removeAt(inx)
        _completeListLd.value = completelist
    }

    // 한 리사이클러뷰 내에서 순서 변경
    fun changeTodoOrder(fromPos: Int, toPos: Int){
        val fromTemp = Card(todolist[fromPos].id, todolist[fromPos].title, todolist[fromPos].content, todolist[fromPos].device)
        val toTemp = Card(todolist[toPos].id, todolist[toPos].title, todolist[toPos].content, todolist[toPos].device)

        todolist[fromPos] = toTemp
        todolist[toPos] = fromTemp
        _todoListLd.value = todolist
    }

}