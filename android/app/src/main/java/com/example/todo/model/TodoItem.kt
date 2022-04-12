package com.example.todo.model

data class TodoItem(
    var title: String,
    var content: String,
    var type: ProgressType,
    var itemId: Int = -1,
    var next: TodoItem? = null
) {

}




