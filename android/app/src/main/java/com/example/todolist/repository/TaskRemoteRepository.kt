package com.example.todolist.repository

import com.example.todolist.network.Result
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.CommonResponse
import com.example.todolist.model.response.TasksResponse

class TaskRemoteRepository(
    private val taskRemoteDataSource: TaskRemoteDataSource,
) {

    suspend fun loadTask(): Result<TasksResponse> {
        val response = taskRemoteDataSource.loadTasks()
        response?.let {
            return Result.Success(it)
        }
        return Result.Error("error")
    }

    suspend fun addTask(cardData: Task): Result<CommonResponse> {
        val response = taskRemoteDataSource.addTask(cardData)
        response?.let {
            return Result.Success(it)
        }
        return Result.Error("error")
    }

    suspend fun modifyTask(modifyTaskRequest: ModifyTaskRequest): Result<CommonResponse> {
        val response = taskRemoteDataSource.modifyTask(modifyTaskRequest)
        response?.let {
            return Result.Success(it)
        }
        return Result.Error("error")
    }
}