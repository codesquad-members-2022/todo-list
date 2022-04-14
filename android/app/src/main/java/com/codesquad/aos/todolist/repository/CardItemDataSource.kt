package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.GetCardResponse
import com.codesquad.aos.todolist.data.model.handlecard.AddCard
import com.codesquad.aos.todolist.data.model.handlecard.EditCard
import com.codesquad.aos.todolist.data.model.handlecard.HandleCardResponse
import retrofit2.Response

interface CardItemDataSource {

    suspend fun getCardItem(): Response<GetCardResponse>
    suspend fun addCardItem(addCardData: AddCard): Response<HandleCardResponse>
    suspend fun editCardItem(cardId: Int, editCardData: EditCard): Response<HandleCardResponse>
}