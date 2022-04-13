package com.example.todolist.repository

import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.Result
import com.example.todolist.model.response.TaskDetailResponse
import com.example.todolist.model.response.TasksResponse

class TaskRemoteRepository(
    private val taskRemoteDataSource: TaskRemoteDataSource
) {

    suspend fun loadTask(): TasksResponse? {
        return taskRemoteDataSource.loadTasks()
    }

    suspend fun addTask(cardData: Task): Result? {
        return taskRemoteDataSource.addTask(cardData)
    }

    suspend fun modifyTask(modifyTaskRequest: ModifyTaskRequest): TaskDetailResponse? {
        return taskRemoteDataSource.modifyTask(modifyTaskRequest)?.taskDetailResponse
    }
}