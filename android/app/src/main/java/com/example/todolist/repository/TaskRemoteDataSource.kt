package com.example.todolist.repository

import android.util.Log
import com.example.todolist.model.Task
import com.example.todolist.model.request.ModifyTaskRequest
import com.example.todolist.model.response.Result
import com.example.todolist.model.response.TasksResponse
import com.example.todolist.network.RetrofitAPI

class TaskRemoteDataSource : TaskDataSource {

    override suspend fun loadTasks(): TasksResponse? {
        var task: TasksResponse? = null

        val service = RetrofitAPI.service
        val response = service.loadTasks()
        if (response.isSuccessful) {
            val result = response.body()
            task = result
            Log.d("카드 조회 성공", "$result")
        } else {
            // TODO 실패했을 시 담을 데이터 정하기
            Log.d("카드 조회 실패", "${response.code()}")
        }
        return task
    }

    override suspend fun addTask(cardData: Task): Result? {
        var task: Result? = null

        val service = RetrofitAPI.service
        val response = service.saveTask(cardData)
        if (response.isSuccessful) {
            val result = response.body()
            task = result
            Log.d("카드 저장 성공", "$result")
        } else {
            // TODO 실패했을 시 담을 데이터 정하기
            Log.d("카드 저장 실패", "${response.code()}")
        }
        return task
    }

    override suspend fun modifyTask(modifyTaskRequest: ModifyTaskRequest): Result? {
        var task: Result? = null

        val service = RetrofitAPI.service
        val response = service.modifyTask(modifyTaskRequest.id, modifyTaskRequest)
        if (response.isSuccessful) {
            val result = response.body()
            task = result
            Log.d("카드 수정 성공", "$result")
        } else {
            // TODO 실패했을 시 담을 데이터 정하기
            Log.d("카드 수정 실패", "${response.code()}")
        }
        return task
    }
}