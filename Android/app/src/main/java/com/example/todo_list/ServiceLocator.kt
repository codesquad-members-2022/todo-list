package com.example.todo_list

import com.example.todo_list.data.Repository
import com.example.todo_list.data.TasksRepository

object ServiceLocator {
    private var taskRepository: Repository? = null

    fun provideRepository(): Repository {
        synchronized(this) {
            return taskRepository ?: taskRepository ?: TasksRepository()
        }
    }
}