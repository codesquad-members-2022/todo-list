package com.example.todo.model

import android.provider.Settings.Global.getString
import androidx.annotation.DrawableRes
import com.example.todo.R
import com.example.todo.common.ProgressType
import java.time.LocalDateTime

enum class ActionType( val value: Int) {
    ADD(R.string.action_add), REMOVE((R.string.action_remove)), MOVE((R.string.action_move)), UPDATE(
        R.string.action_update
    ),
}


data class ActionLog(
    val title: String,
    val actionType: ActionType,
    val time: LocalDateTime,
    val nowProgressType: ProgressType,
    val prevProgressType: ProgressType? = null
) {

    override fun toString(): String {
        return when (actionType) {
            ActionType.ADD -> {
                "${nowProgressType.value}에 ${title}을 ${actionType.value}합니다"
            }
            ActionType.REMOVE -> {
                "${nowProgressType.value}에서 ${title}을 ${actionType.value}합니다"
            }
            ActionType.MOVE -> {
                "${title}을 ${prevProgressType?.value}에서 ${nowProgressType.value}로 ${actionType.value}합니다"
            }
            ActionType.UPDATE -> {
                "${title}을 ${actionType.value}합니다"
            }
        }
    }
}
