package com.example.todo.network

// Insert
data class CardIndex(
    val card_id: Int
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

data class Ing(
    val content: String,
    val id: Int,
    val nextId: Int,
    val title: String,
    val uploadDateTime: String,
    val writer: String
)


data class Done(
    val content: String,
    val id: Int,
    val nextId: Int,
    val title: String,
    val uploadDateTime: String,
    val writer: String
)


data class CardsClassifiedByLocation(
    val todo: List<Todo>,
    val ing: List<Ing>,
    val done: List<Done>
)