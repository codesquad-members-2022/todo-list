package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.GetCardResponse
import retrofit2.Response

interface CardItemDataSource {

    suspend fun getCardItem(): Response<GetCardResponse>
}