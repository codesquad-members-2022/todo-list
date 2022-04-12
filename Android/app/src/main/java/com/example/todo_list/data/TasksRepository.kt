package com.example.todo_list.data

import com.example.todo_list.history.data.HistoryCard
import com.example.todo_list.network.NetworkModule
import retrofit2.Response

class TasksRepository : Repository {
    private val network = NetworkModule.service

    override suspend fun getHistories(): Response<List<HistoryCard>> {
        return network.getHistories("histories")
    }
}