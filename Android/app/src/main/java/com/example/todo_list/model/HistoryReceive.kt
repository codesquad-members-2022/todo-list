package com.example.todo_list.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class HistoryReceive {
    companion object RetrofitApiObject {
        private val retrofit =
            Retrofit.Builder()
                .baseUrl("https://f278a12c-c825-466b-aa01-65337bbdf28a.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service: HistoryApi = retrofit.create(HistoryApi::class.java)
    }
}

interface HistoryApi {
    @GET("api/{todos}") // 어노테이션
    fun getHistory(@Path("todos") variable: String): Call<List<HistoryCard>>

    @GET("api/{todos}")
    fun getTodos(@Path("todos") variable: String): Call<List<Todos>>
}