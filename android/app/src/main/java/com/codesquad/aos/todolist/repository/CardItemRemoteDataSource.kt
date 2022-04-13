package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.GetCardResponse
import com.codesquad.aos.todolist.network.ApiClient

class CardItemRemoteDataSource(private val api: ApiClient) : CardItemDataSource {

    override suspend fun getCardItem(): GetCardResponse {
        return api.getCard()
    }

}