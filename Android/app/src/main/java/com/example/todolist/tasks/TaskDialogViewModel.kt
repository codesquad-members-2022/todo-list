package com.example.todolist.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.data.Repository

class TaskDialogViewModel(private val repository: Repository) : ViewModel() {
    var checkTitle = MutableLiveData(false)
    var checkBody = MutableLiveData(false)
}