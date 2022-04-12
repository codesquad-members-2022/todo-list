package com.example.todo_list

import com.example.todo_list.history.data.HistoryCard
import retrofit2.Response

interface Repository {
    suspend fun getHistories(): Response<List<HistoryCard>>
}