package com.example.todolist.network

import com.example.todolist.data.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {
    @Headers("user:1")
    @GET("/api/todo")
    suspend fun getCards(): Response<CardResponse>

    @POST("/api/todo")
    suspend fun addCard(
        @Body newCard: NewCard,
    ): Response<Card>

    @DELETE("/api/todo/card/{id}")
    suspend fun deleteCard(
        @Path("id") cardId: Int
    ): Response<Unit>

    @PATCH("/api/todo/card/{id}/move")
    suspend fun moveCard(
        @Path("id") cardId: Int,
        @Body movedCard: MovedCard
    )

    @Headers("user:1")
    @GET("/api/todo/history")
    suspend fun getHistories(): Response<HistoryResponse>

    companion object {

        private const val baseUrl = "http://34.196.35.151:8080"

        fun create(): ApiClient {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)

        }

    }
}