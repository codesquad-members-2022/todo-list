package com.codesquad.aos.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codesquad.aos.todolist.data.model.Card

class TodoViewModel: ViewModel() {

    private val _todo_list_ld = MutableLiveData<MutableList<Card>>()
    private var todo_list = mutableListOf<Card>()
    val todo_list_ld: LiveData<MutableList<Card>> get() = _todo_list_ld

    // 해야할 일 추가
    fun addTodo(title: String, content: String){
        todo_list.add(0, Card((todo_list.size + 1), title, content, "author by android"))
        _todo_list_ld.value = todo_list
    }
}