package com.example.todo.respository

import android.content.Context
import com.example.todo.R
import com.example.todo.model.ActionLog
import com.example.todo.model.ActionType
import com.example.todo.model.ProgressType
import com.example.todo.network.JsonActionLog

class ActionLogRepository(
    private val context: Context,
    private val actionLogDataSource: ActionLogDataSource
) {
    suspend fun getActionLogs(): List<ActionLog> {
        val jsonActionLogs = actionLogDataSource.getActionLogs()
        return jsonActionLogs.map { stringActionLogToActionLog(it) }
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