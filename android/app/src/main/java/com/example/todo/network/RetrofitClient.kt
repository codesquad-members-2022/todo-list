package com.example.todo.network

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitClient {
    @POST("card")
    suspend fun getCardIdx(
        @Query("title") title: String,
        @Query("content") content: String,
        @Query("writer") writer: String,
        @Query("current_location") current_location: String
    ): Response<CardIndex>

//    "title" : "카드제목",
//    "content" : "카드내용",
//    "writer" : "작성자",
//    "current_location" : "done"

    @GET("cards")
    suspend fun getTodos(): List<JsonTodo>

    @GET("histories")
    suspend fun getActionLog(): ActionLogResponse

    companion object {
        private const val baseUrl = "http://52.78.46.69/"

        fun create(): RetrofitClient {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitClient::class.java)
        }
    }
}

