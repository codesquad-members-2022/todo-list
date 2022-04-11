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
            Status.TODO -> tasks.todo.add(TaskDetailResponse(todoIndex++,
                task.title,
                task.content,
                task.status))
            Status.IN_PROGRESS -> tasks.inProgress.add(TaskDetailResponse(inProgressIndex++,
                task.title,
                task.content,
                task.status))
            Status.DONE -> tasks.done.add(TaskDetailResponse(doneIndex++,
                task.title,
                task.content,
                task.status))
        }
        return tasks
    }

    fun getHistory(): List<History> {
        return listOf(
            History(1, ActionType.MOVE, "HTML/CSS공부하기", "해야할 일", "하고 있는 일", "2022-04-05 21:19:00"),
            History(2, ActionType.ADD, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
            History(3, ActionType.REMOVE, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
            History(4, ActionType.UPDATE, "HTML/CSS공부하기", "해야할 일", null, "2022-04-05 21:05:03"),
        )
    }
}