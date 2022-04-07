package com.example.todo.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitClient {
    @GET("/cards")
    suspend fun getTodos(): List<JsonTodo>

    @GET("/histories")
    suspend fun getActionLog(): List<JsonActionLog>

    companion object {
        private const val baseUrl = "http://52.78.46.69"

        fun create(): RetrofitClient {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitClient::class.java)
        }
    }
}

