package com.example.todolist.data

import com.example.todolist.network.ApiClient
import retrofit2.Response

class HistoryRemoteDataSource(private val apiClient: ApiClient) : HistoryDataSource {

    override suspend fun getHistories(): Response<HistoryResponse> {
        return apiClient.getHistories()
    }

}