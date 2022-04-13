package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.Card

class CardItemRepository(private val remoteDataSource: CardItemRemoteDataSource) {

    suspend fun getCardItems(): List<Card> {
        return remoteDataSource.getCardItem()
    }

}