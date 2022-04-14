package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.GetCardResponse

class CardItemRepository(private val remoteDataSource: CardItemRemoteDataSource) {

    suspend fun getCardItems(): GetCardResponse {
        return remoteDataSource.getCardItem()
    }

}