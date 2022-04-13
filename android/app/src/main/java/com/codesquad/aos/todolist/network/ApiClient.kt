package com.codesquad.aos.todolist.network

import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.GetCardResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiClient {

    @GET("api/cards")
    suspend fun getCard(): GetCardResponse

    companion object {

        private const val baseUrl = "http://ec2-3-35-24-197.ap-northeast-2.compute.amazonaws.com:8080/"

        fun create(): ApiClient {

            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
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