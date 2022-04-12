package com.example.todolist.repository

import com.example.todolist.model.Task
import com.example.todolist.model.response.AddTasksResponse
import com.example.todolist.model.response.TasksResponse

interface TaskDataSource {

    suspend fun loadTasks(): TasksResponse?

    suspend fun addTask(cardData: Task): AddTasksResponse?
}