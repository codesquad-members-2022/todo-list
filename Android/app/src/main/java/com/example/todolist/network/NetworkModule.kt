package com.example.todolist.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {
    companion object RetrofitApiObject {
//        private const val BASE_URL = "https://f278a12c-c825-466b-aa01-65337bbdf28a.mock.pstmn.io/"
        private const val BASE_URL = "http://ec2-52-79-144-74.ap-northeast-2.compute.amazonaws.com/"
        private val client = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()

        private val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service: TodoService = retrofit.create(TodoService::class.java)
    }
}
