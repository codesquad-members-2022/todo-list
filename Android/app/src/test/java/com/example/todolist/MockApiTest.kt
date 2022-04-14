package com.example.todolist

import com.example.todolist.data.TasksRepository
import com.example.todolist.tasks.data.Task
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MockApiTest {
    @Test
    fun getAllTasks() = runBlocking {
        val expectedTasksList = listOf(
            Task(
                1,
                "Github 공부하기",
                "add, commit, push",
                "sam",
                "todo",
                "2022-04-06T15:30:00.000+09:00",
                "2022-04-06T15:30:00.000+09:00"
            ),
            Task(
                2,
                "블로그에 포스팅할 것",
                "*Github 공부내용 \r\n *모던 자바스크립트 1장 공부내용",
                "sam",
                "todo",
                "2022-04-05T14:30:00.000+09:00",
                "2022-04-05T14:30:00.000+09:00"
            ),
            Task(
                3,
                "HTMl/CSS 공부하기",
                "input 태그 실습",
                "sam",
                "doing",
                "2022-04-04T15:30:00.000+09:00",
                "2022-04-05T12:15:00.000+09:00"
            ),
            Task(
                4,
                "여자친구와 데이트",
                "초밥 먹으러 가기",
                "sam",
                "done",
                "2022-04-02T15:30:00.000+09:00",
                "2022-04-05T10:15:00.000+09:00"
            )
        )

        val repository = TasksRepository()
        val actualTask = repository.getAllTasks().body()!!
        assertThat(actualTask, `is`(not(nullValue())))
        //assertThat(result, `is`(tasksList))

        actualTask.forEachIndexed { index, task ->
            assertThat(task, `is`(expectedTasksList[index]))
        }
    }

    @Test
    fun getTask() = runBlocking {
        val expectedTask = Task(
            1,
            "Github 공부하기",
            "add, commit, push",
            "sam",
            "todo",
            "2022-04-06T15:30:00.000+09:00",
            "2022-04-06T15:30:00.000+09:00"
        )

        val repository = TasksRepository()
        val actualTask = repository.getTask(1).body()!!
        assertThat(actualTask, `is`(not(nullValue())))
        assertThat(expectedTask, `is`(actualTask))
    }

    @Test
    fun createTask() = runBlocking {
        val expectedTask = Task(
            1,
            "Github 공부하기",
            "add, commit, push",
            "sam",
            "todo",
            "2022-04-06T15:30:00.000+09:00",
            "2022-04-06T15:30:00.000+09:00"
        )

        val repository = TasksRepository()
        val result = repository.createTask(
            "Github 공부하기",
            "add, commit, push",
            "sam",
            "todo"
        )

        assertThat(result.body(), `is`(expectedTask))
    }

    @Test
    fun deleteTask() = runBlocking {
        val repository = TasksRepository()
        repository.deleteTask(1)
    }

    @Test
    fun updateTask() = runBlocking {
        val expectedTask = Task(
            1,
            "sql 공부하기",
            "add, commit, push",
            "sam",
            "todo",
            "2022-04-06T15:30:00.000+09:00",
            "2022-04-06T15:55:00.000+09:00"
        )

        val repository = TasksRepository()
        val actualTask = repository.updateTask(
            1,
            hashMapOf(
                "title" to "sql 공부하기"
            )
        ).body()

        assertThat(actualTask, `is`(expectedTask))
    }
}