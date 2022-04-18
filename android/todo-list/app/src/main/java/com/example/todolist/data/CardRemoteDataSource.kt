package com.example.todolist.data

import com.example.todolist.network.ApiClient
import retrofit2.Response

class CardRemoteDataSource(private val apiClient: ApiClient) : CardDataSource {

    override suspend fun getCards(): Response<CardResponse> {
        return apiClient.getCards()
    }

    override suspend fun addCard(newCard: NewCard): Response<Card> {
        return apiClient.addCard(newCard)
    }

    override suspend fun deleteCard(cardId: Int) {
        apiClient.deleteCard(cardId)
    }

    override suspend fun moveCard(movedCard: MovedCard) {
        apiClient.moveCard(movedCard.cardId, movedCard)
    }

}
