package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.GetCardResponse
import com.codesquad.aos.todolist.data.model.handlecard.AddCard
import com.codesquad.aos.todolist.data.model.handlecard.EditCard
import com.codesquad.aos.todolist.data.model.handlecard.HandleCardResponse
import com.codesquad.aos.todolist.network.ApiClient
import retrofit2.Response

class CardItemRemoteDataSource(private val api: ApiClient) : CardItemDataSource {

    override suspend fun getCardItem(): Response<GetCardResponse> {
        return api.getCard()
    }

    override suspend fun addCardItem(addCardData: AddCard): Response<HandleCardResponse> {
        return api.addCard(addCardData)
    }

    override suspend fun editCardItem(
        cardId: Int,
        editCardData: EditCard
    ): Response<HandleCardResponse> {
        return api.editCard(cardId, editCardData)
    }

}