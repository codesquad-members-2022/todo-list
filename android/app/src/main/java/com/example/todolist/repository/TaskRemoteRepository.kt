package com.example.todolist.repository

import com.example.todolist.model.Status
import com.example.todolist.model.Task
import com.example.todolist.model.response.AddTasksResponse
import com.example.todolist.model.response.TaskDetailResponse
import com.example.todolist.model.response.TasksResponse

class TaskRemoteRepository(
    private val taskRemoteDataSource: TaskRemoteDataSource
) {

    suspend fun loadTask(): TasksResponse? {
        return taskRemoteDataSource.loadTasks()
    }

    suspend fun addTask(cardData: Task): AddTasksResponse? {
        return taskRemoteDataSource.addTask(cardData)
    }
}