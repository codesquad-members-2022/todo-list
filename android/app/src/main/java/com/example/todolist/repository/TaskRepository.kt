package com.example.todolist.repository

import com.example.todolist.model.*
import com.example.todolist.model.response.TaskDetailResponse
import com.example.todolist.model.response.TasksResponse
import java.util.*

class TaskRepository {

    private var todoIndex = 4
    private var inProgressIndex = 4
    private var doneIndex = 4
    private val tasks = TasksResponse(
        mutableListOf(
            TaskDetailResponse(1, "GitHub 공부하기", "add, commit, push", Status.TODO, "Android"),
            TaskDetailResponse(
                2,
                "블로그에 포스팅할 것",
                "• GitHub 공부내용\n• 모던 자바스크립트 1장 공부내용",
                Status.TODO,
                "Android"
            ),
            TaskDetailResponse(3, "HTML/CSS", "input 태그 실습", Status.TODO, "Android")
        ),
        mutableListOf(
            TaskDetailResponse(
                4,
                "GitHub 공부하기",
                "add, commit, push",
                Status.IN_PROGRESS,
                "Android"
            ),
            TaskDetailResponse(
                5,
                "블로그에 포스팅할 것",
                "• GitHub 공부내용\n• 모던 자바스크립트 1장 공부내용",
                Status.IN_PROGRESS,
                "Android"
            ),
            TaskDetailResponse(6, "HTML/CSS", "input 태그 실습", Status.IN_PROGRESS, "Android")
        ),
        mutableListOf(
            TaskDetailResponse(7, "GitHub 공부하기", "add, commit, push", Status.DONE, "Android"),
            TaskDetailResponse(
                8,
                "블로그에 포스팅할 것",
                "• GitHub 공부내용\n• 모던 자바스크립트 1장 공부내용",
                Status.DONE,
                "Android"
            ),
            TaskDetailResponse(9, "HTML/CSS", "input 태그 실습", Status.DONE, "Android")
        ),
    )

    fun getTasks(): TasksResponse {
        return tasks
    }

    fun addTask(task: Task): TasksResponse {
        when (task.status) {
            Status.TODO -> tasks.todo.add(
                0, TaskDetailResponse(
                    todoIndex++,
                    task.title,
                    task.content,
                    task.status
                )
            )
            Status.IN_PROGRESS -> tasks.inProgress.add(
                0, TaskDetailResponse(
                    inProgressIndex++,
                    task.title,
                    task.content,
                    task.status
                )
            )
            Status.DONE -> tasks.done.add(
                0, TaskDetailResponse(
                    doneIndex++,
                    task.title,
                    task.content,
                    task.status
                )
            )
        }
        return tasks
    }

    fun updateTask(task: TaskDetailResponse): TasksResponse {
        when (task.status) {
            Status.TODO -> {
                val originalTask = tasks.todo.find { task.id == it.id }
                val index = tasks.todo.indexOf(originalTask)
                tasks.todo[index] = task
            }
            Status.IN_PROGRESS -> {
                val originalTask = tasks.inProgress.find { task.id == it.id }
                val index = tasks.inProgress.indexOf(originalTask)
                tasks.inProgress[index] = task
            }
            Status.DONE -> {
                val originalTask = tasks.done.find { task.id == it.id }
                val index = tasks.done.indexOf(originalTask)
                tasks.done[index] = task
            }
        }
        return tasks
    }

    fun moveDone(task: TaskDetailResponse): TasksResponse {
        if (task.status != Status.DONE) {
            tasks.done.add(0, task.copy(status = Status.DONE))
            deleteTask(task)
        }
        return tasks
    }

    fun deleteTask(task: TaskDetailResponse): TasksResponse {
        when (task.status) {
            Status.TODO -> tasks.todo.remove(task)
            Status.IN_PROGRESS -> tasks.inProgress.remove(task)
            else -> tasks.done.remove(task)
        }
        return tasks
    }

    fun swap(
        currentList: List<TaskDetailResponse>,
        fromPosition: Int,
        toPosition: Int,
    ): TasksResponse {
        when (currentList) {
            tasks.todo -> Collections.swap(tasks.todo, fromPosition, toPosition)
            tasks.inProgress -> Collections.swap(tasks.inProgress, fromPosition, toPosition)
            tasks.done -> Collections.swap(tasks.done, fromPosition, toPosition)
        }
        return tasks
    }

    fun getHistory(): List<History> {
        return listOf(
            History(4, ActionType.MOVE, "HTML/CSS공부하기", "해야할 일", "하고 있는 일", "2022-04-05 21:19:00"),
            History(3, ActionType.ADD, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
            History(2, ActionType.REMOVE, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
            History(1, ActionType.UPDATE, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
        )
    }
}