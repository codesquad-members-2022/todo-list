package com.example.todo_list.data

import com.example.todo_list.history.data.HistoryCard
import com.example.todo_list.network.NetworkModule
import com.example.todo_list.tasks.data.Task
import retrofit2.Response

class TasksRepository : Repository {
    private val network = NetworkModule.service

    override suspend fun getHistories(): Response<List<HistoryCard>> {
        return network.getHistories("histories")
    }

    override suspend fun getAllTasks(): Response<List<Task>> {
        return network.getAllTasks()
    }

    override suspend fun getTask(id: Int): Response<Task> {
        return network.getTask(id)
    }

    override suspend fun createTask(
        title: String,
        contents: String,
        user: String,
        status: String
    ): Response<Task> {
        return network.createTask(
            title,
            contents,
            user,
            status
        )
    }

    override suspend fun deleteTask(id: Int) {
        network.deleteTask(id)
    }

    override suspend fun updateTask(id: Int, param: HashMap<String, String>): Response<Task> {
        return network.updateTask(
            id,
            param
        )
    }
}