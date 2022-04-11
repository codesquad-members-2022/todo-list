package com.example.todolist.repository

import com.example.todolist.model.*

class TaskRepository {

    private var todoIndex = 4
    private var inProgressIndex = 4
    private var doneIndex = 4
    private val tasks = TasksResponse(
        mutableListOf(
            TaskDetailResponse(1, "GitHub 공부하기", "add, commit, push", Status.TODO, "Android"),
            TaskDetailResponse(2,
                "블로그에 포스팅할 것",
                "• GitHub 공부내용\n• 모던 자바스크립트 1장 공부내용",
                Status.TODO,
                "Android"),
            TaskDetailResponse(3, "HTML/CSS", "input 태그 실습", Status.TODO, "Android")),
        mutableListOf(
            TaskDetailResponse(1,
                "GitHub 공부하기",
                "add, commit, push",
                Status.IN_PROGRESS,
                "Android"),
            TaskDetailResponse(2,
                "블로그에 포스팅할 것",
                "• GitHub 공부내용\n• 모던 자바스크립트 1장 공부내용",
                Status.IN_PROGRESS,
                "Android"),
            TaskDetailResponse(3, "HTML/CSS", "input 태그 실습", Status.IN_PROGRESS, "Android")),
        mutableListOf(
            TaskDetailResponse(1, "GitHub 공부하기", "add, commit, push", Status.DONE, "Android"),
            TaskDetailResponse(2,
                "블로그에 포스팅할 것",
                "• GitHub 공부내용\n• 모던 자바스크립트 1장 공부내용",
                Status.DONE,
                "Android"),
            TaskDetailResponse(3, "HTML/CSS", "input 태그 실습", Status.DONE, "Android")),
    )

    fun getTasks(): TasksResponse {
        return tasks
    }

    fun addTask(task: Task): TasksResponse {
        when (task.status) {
            Status.TODO -> tasks.todo.add(0, TaskDetailResponse(todoIndex++,
                task.title,
                task.content,
                task.status))
            Status.IN_PROGRESS -> tasks.inProgress.add(0, TaskDetailResponse(inProgressIndex++,
                task.title,
                task.content,
                task.status))
            Status.DONE -> tasks.done.add(0, TaskDetailResponse(doneIndex++,
                task.title,
                task.content,
                task.status)
            )
        }
        return tasks
    }

    fun moveDone(task: TaskDetailResponse): TasksResponse {
        deleteTask(task)
        if (task.status == Status.TODO) {
            task.status = Status.DONE
            tasks.done.add(0, task)
        } else if (task.status == Status.IN_PROGRESS) {
            task.status = Status.DONE
            tasks.done.add(0, task)
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

    fun getHistory(): List<History> {
        return listOf(
            History(4, ActionType.MOVE, "HTML/CSS공부하기", "해야할 일", "하고 있는 일", "2022-04-05 21:19:00"),
            History(3, ActionType.ADD, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
            History(2, ActionType.REMOVE, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
            History(1, ActionType.UPDATE, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
        )
    }
}