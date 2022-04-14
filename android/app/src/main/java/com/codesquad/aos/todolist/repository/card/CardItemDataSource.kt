package com.codesquad.aos.todolist.repository.card

import com.codesquad.aos.todolist.data.model.GetCardResponse
import com.codesquad.aos.todolist.data.model.handlecard.AddCard
import com.codesquad.aos.todolist.data.model.handlecard.EditCard
import com.codesquad.aos.todolist.data.model.handlecard.HandleCardResponse
import com.codesquad.aos.todolist.data.model.handlecard.MoveCard
import retrofit2.Response

interface CardItemDataSource {

    suspend fun getCardItem(): Response<GetCardResponse>
    suspend fun addCardItem(addCardData: AddCard): Response<HandleCardResponse>
    suspend fun editCardItem(cardId: Int, editCardData: EditCard): Response<HandleCardResponse>
    suspend fun deleteCardItem(cardId: Int): Response<HandleCardResponse>
    suspend fun moveCardItem(cardId: Int, moveCardData: MoveCard): Response<HandleCardResponse>
}