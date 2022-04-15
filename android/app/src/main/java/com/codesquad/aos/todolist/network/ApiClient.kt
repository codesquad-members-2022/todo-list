package com.codesquad.aos.todolist.network

import com.codesquad.aos.todolist.data.model.handlecard.AddCard
import com.codesquad.aos.todolist.data.model.GetCardResponse
import com.codesquad.aos.todolist.data.model.LogX
import com.codesquad.aos.todolist.data.model.handlecard.EditCard
import com.codesquad.aos.todolist.data.model.handlecard.HandleCardResponse
import com.codesquad.aos.todolist.data.model.handlecard.MoveCard
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

    // 카드 이동
    @PUT("api/cards/{id}/move")
    suspend fun moveCard(
        @Path("id") id:Int,
        @Body moveCardData: MoveCard
    ): Response<HandleCardResponse>

    // 로그 조회, 몇 개씩??
    @GET("api/logs")
    suspend fun getLogs(
        @Query("nowNumberOfLogs") nowNumberOfLogs: Int,
        @Query("size") size: Int
    ): List<LogX>

    // 카드 order값 재배치
    @GET("api/batch-process")
    suspend fun batchProcess(): Response<Void>

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