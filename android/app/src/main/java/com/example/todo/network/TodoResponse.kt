package com.example.todo.network

import com.google.gson.annotations.SerializedName

// Insert
data class CardIndex(
    val cardId: Int
)

data class TodoResponse(
    val cardsClassifiedByLocation: CardsClassifiedByLocation
)

abstract class TodoResponseItem(
    open val content: String,
    open val id: Int,
    open val nextId: Int,
    open val title: String,
    open val uploadDateTime: String,
    open val writer: String
)

data class Todo(
    val content: String,
    val id: Int,
    val nextId: Int,
    val title: String,
    val uploadDateTime: String,
    val writer: String
)

data class CardsClassifiedByLocation(
    val todo: List<Todo>,
    val ing: List<Todo>,
    val done: List<Todo>
)