package com.example.todolist

import com.example.todolist.data.Repository
import com.example.todolist.data.TasksRepository

object ServiceLocator {
    private var taskRepository: Repository? = null

    fun provideRepository(): Repository {
        synchronized(this) {
            return taskRepository ?: taskRepository ?: TasksRepository()
        }
    }
}