package com.example.todo.respository

import android.content.Context
import android.util.Log
import com.example.todo.R
import com.example.todo.common.ActionType
import com.example.todo.common.ProgressType
import com.example.todo.model.ActionLog
import com.example.todo.network.JsonActionLog
import com.example.todo.network.RetrofitClient

class ActionLogRemoteDataSource(
    private val retrofitClient: RetrofitClient,
    private val context: Context
) : ActionLogDataSource {
    override suspend fun getActionLogs(): List<ActionLog> {
        val response = retrofitClient.getActionLog()
        val resultList = mutableListOf<ActionLog>()
        response.histories.forEach {
            val actionLog = stringActionLogToActionLog(it)
            resultList.add(actionLog)
        }
        return resultList
    }

    private fun stringActionLogToActionLog(jsonActionLog: JsonActionLog): ActionLog {
        val actionType = stringActionTypeToEnum(jsonActionLog.actionType)
        val pastProgressType = stringProgressTypeToEnum(jsonActionLog.pastLocation)
        val nowProgressType = stringProgressTypeToEnum(jsonActionLog.nowLocation)

        return ActionLog(
            jsonActionLog.cardTitle,
            actionType,
            jsonActionLog.historyDate,
            pastProgressType,
            nowProgressType
        )
    }

    private fun stringProgressTypeToEnum(stringProgressType: String): ProgressType {
        return when (stringProgressType) {
            context.getString(R.string.progress_todo) -> ProgressType.TO_DO
            context.getString(R.string.progress_ing) -> ProgressType.IN_PROGRESS
            else -> ProgressType.DONE
        }
    }

    private fun stringActionTypeToEnum(stringActionType: String): ActionType {
        return when (stringActionType) {
            context.getString(R.string.action_add) -> ActionType.ADD
            context.getString(R.string.action_move) -> ActionType.MOVE
            context.getString(R.string.action_remove) -> ActionType.REMOVE
            else -> ActionType.UPDATE
        }
    }
}