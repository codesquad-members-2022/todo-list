package com.example.todo_list.network

import com.example.todo_list.history.data.HistoryCard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoService {

    @GET("api/{histories}")
    suspend fun getHistories(
        @Path("histories") variable: String
    ): Response<List<HistoryCard>>
}