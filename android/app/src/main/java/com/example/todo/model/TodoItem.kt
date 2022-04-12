package com.example.todo.model

data class TodoItem(
    val title: String,
    val content: String,
    val type: ProgressType,
    var itemId: Int = -1,
    var next: TodoItem? = null
) {

}




