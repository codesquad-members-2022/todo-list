package com.example.todolist.repository

import android.util.Log
import com.example.todolist.model.History
import com.example.todolist.network.Result
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.CommonResponse
import com.example.todolist.model.response.TaskDetailResponse
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

    suspend fun addTask(cardData: Task): CommonResponse? {
        return taskRemoteDataSource.addTask(cardData)
    }

    suspend fun modifyTask(modifyTaskRequest: ModifyTaskRequest): TaskDetailResponse? {
        return taskRemoteDataSource.modifyTask(modifyTaskRequest)?.taskDetailResponse
    }

    suspend fun loadHistory(): Result<List<History>> {
        val response = taskRemoteDataSource.loadHistory()
        response?.let {
            Log.d("test", "loadHistory: $it")
            return Result.Success(it)
        }
        return Result.Error("error")
    }
}