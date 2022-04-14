package com.example.todolist.ui.common

import com.example.todolist.data.Card
import com.example.todolist.data.NewCard

interface CardActionHandler {

    fun addCard(card: NewCard)

    fun deleteCard(cardId: Int)

    fun dropCard(draggedCard: Card, targetCard: Card)

}