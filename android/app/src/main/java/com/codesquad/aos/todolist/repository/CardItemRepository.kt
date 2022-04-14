package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.GetCardResponse
import com.codesquad.aos.todolist.data.model.handlecard.AddCard
import com.codesquad.aos.todolist.data.model.handlecard.EditCard
import com.codesquad.aos.todolist.data.model.handlecard.HandleCardResponse
import com.codesquad.aos.todolist.data.model.handlecard.MoveCard
import retrofit2.Response

class CardItemRepository(private val remoteDataSource: CardItemRemoteDataSource) {

    suspend fun getCardItems(): Response<GetCardResponse> {
        return remoteDataSource.getCardItem()
    }

    suspend fun addCardItem(addCardData: AddCard): Response<HandleCardResponse> {
        return remoteDataSource.addCardItem(addCardData)
    }

    suspend fun editCardItem(cardId: Int, editCardData: EditCard): Response<HandleCardResponse> {
        return remoteDataSource.editCardItem(cardId, editCardData)
    }

    suspend fun deleteCardItem(cardId: Int): Response<HandleCardResponse> {
        return remoteDataSource.deleteCardItem(cardId)
    }

    suspend fun moveCardItem(cardId: Int, moveCardData: MoveCard): Response<HandleCardResponse>{
        return remoteDataSource.moveCardItem(cardId, moveCardData)
    }

}