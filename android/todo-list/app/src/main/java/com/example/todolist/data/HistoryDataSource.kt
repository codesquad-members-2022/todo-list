package com.example.todolist.data

import retrofit2.Response

interface HistoryDataSource {

    suspend fun getHistories() : Response<HistoryResponse>

}