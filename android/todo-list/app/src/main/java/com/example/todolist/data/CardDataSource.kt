package com.example.todolist.data

import retrofit2.Response

interface CardDataSource {

    suspend fun getCards(): Response<CardResponse>

    suspend fun addCard(newCard: NewCard): Response<Card>

    suspend fun deleteCard(cardId: Int)

    suspend fun moveCard(movedCard: MovedCard)
}
