package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.network.ApiClient

class CardItemRemoteDataSource(private val api: ApiClient) : CardItemDataSource {

    override suspend fun getCardItem(): List<Card> {
        return api.getCard().toList()
    }

}