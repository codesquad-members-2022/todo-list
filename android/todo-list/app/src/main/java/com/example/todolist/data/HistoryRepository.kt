package com.example.todolist.data

class HistoryRepository(private val historyDataSource: HistoryRemoteDataSource) {

    suspend fun getHistories() =
        historyDataSource.getHistories().body()?.histories

}