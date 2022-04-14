package com.example.todo.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitClient {
    @POST("card")
    suspend fun getCardIdx(
        @Body post: AddPostBody
    ): Response<CardIndex>

    @GET("cards")
    suspend fun getTodos(): Response<TodoResponse>

    @GET("histories")
    suspend fun getActionLog(): Response<ActionLogResponse>

    @DELETE("card/{card_id}")
    suspend fun removeTodo(@Path("card_id") cardId: Int): Response<Void>

    @PATCH("card/{card_id}")
    suspend fun updateTodo(@Path("card_id") cardId: Int,@Body body :UpdateTodoBody): Response<Void>

    companion object {
        private const val baseUrl = "http://3.37.194.187:8080/"
        fun create(): RetrofitClient {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitClient::class.java)
        }
    }
}

