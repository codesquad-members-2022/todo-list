package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.GetCardResponse
import retrofit2.Response

class CardItemRepository(private val remoteDataSource: CardItemRemoteDataSource) {

    suspend fun getCardItems(): Response<GetCardResponse> {
        return remoteDataSource.getCardItem()
    }

}