package com.codesquad.aos.todolist.network

import com.codesquad.aos.todolist.data.model.handlecard.AddCard
import com.codesquad.aos.todolist.data.model.GetCardResponse
import com.codesquad.aos.todolist.data.model.handlecard.EditCard
import com.codesquad.aos.todolist.data.model.handlecard.HandleCardResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiClient {

    // 전체 카드 조회
    @GET("api/cards")
    suspend fun getCard(): Response<GetCardResponse>

    // 카드 추가
    @POST("api/cards")
    suspend fun addCard(
        @Body addCardData: AddCard
    ): Response<HandleCardResponse>

    // 카드 삭제
    @DELETE("api/cards/{id}")
    suspend fun deleteCard(
        @Path("id") id: Int
    ): Response<HandleCardResponse>

    // 카드 수정
    @PUT("api/cards/{id}")
    suspend fun editCard(
        @Path("id") id: Int,
        @Body addCardData: EditCard
    ): Response<HandleCardResponse>

//    @PUT("api/cards/{id}/move")
//    suspend fun moveCard(
//        @Path("id") id:Int,
//    )

    companion object {

        private const val baseUrl =
            "http://ec2-3-35-24-197.ap-northeast-2.compute.amazonaws.com:8080/"

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