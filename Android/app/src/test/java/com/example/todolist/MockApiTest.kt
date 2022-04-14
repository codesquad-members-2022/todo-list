package com.example.todolist

import com.example.todolist.data.TasksRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MockApiTest {
    @Test
    fun getAllTasks() = runBlocking {
        val repository = TasksRepository()

        val result = repository.getAllTasks()
        println(result.body())
    }

    @Test
    fun getTask() = runBlocking {
        val repository = TasksRepository()

        val result = repository.getTask(1)
        println(result.body())
    }

    @Test
    fun createTask() = runBlocking {
        val repository = TasksRepository()

        val result = repository.createTask(
            "Github 공부하기",
            "add, commit, push",
            "sam",
            "todo"
        )
        println(result.body().toString())
    }

    @Test
    fun deleteTask() = runBlocking {
        val repository = TasksRepository()

        repository.deleteTask(1)
    }

    @Test
    fun updateTask() = runBlocking {
        val repository = TasksRepository()

        val result = repository.updateTask(
            1,
            hashMapOf(
                "title" to "sql 공부하기"
            )
        )
        println(result.body())
    }
}