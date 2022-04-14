package com.example.todo.model

data class TodoItem(
    var title: String,
    var content: String,
    var type: ProgressType,
    var itemId: Int? = null,
    var next: Int? = null
) {

}




