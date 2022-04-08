package com.example.todo.model

import com.example.todo.common.ProgressType

data class TodoItem(var itemId: String, val title: String, val content: String, val type: ProgressType)




