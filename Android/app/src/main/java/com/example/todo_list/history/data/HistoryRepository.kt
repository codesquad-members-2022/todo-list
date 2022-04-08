package com.example.todo_list.history.data

import com.example.todo_list.Repository
import com.example.todo_list.history.HistoryReceive
import retrofit2.Response

class HistoryRepository : Repository {
    private val retrofit = HistoryReceive.service

    override suspend fun getHistory(): Response<List<HistoryCard>> {
        return retrofit.getHistory("histories")
    }
}