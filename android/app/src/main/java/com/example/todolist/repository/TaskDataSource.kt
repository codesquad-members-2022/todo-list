package com.example.todolist.repository

import com.example.todolist.model.History
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.CommonResponse
import com.example.todolist.model.response.TasksResponse

interface TaskDataSource {

    suspend fun loadTasks(): TasksResponse?

    suspend fun addTask(cardData: Task): CommonResponse?

    suspend fun modifyTask(modifyTaskRequest: ModifyTaskRequest): CommonResponse?

    suspend fun loadHistory(): List<History>?

    suspend fun deleteTask(id: Int): CommonResponse?

    suspend fun moveTask(id: Int, status: String): CommonResponse?
}