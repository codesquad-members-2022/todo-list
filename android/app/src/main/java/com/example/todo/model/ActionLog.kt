package com.example.todo.model

import com.example.todo.common.ActionType
import com.example.todo.common.ProgressType

data class ActionLog(
    val title: String,
    val actionType: ActionType,
    val time: String,
    val nowProgressType: ProgressType,
    val prevProgressType: ProgressType
) {

//    override fun toString(): String {
//        return when (actionType) {
//            ActionType.ADD -> {
//                "${nowProgressType.value}에 ${title}을 ${actionType.value}합니다"
//            }
//            ActionType.REMOVE -> {
//                "${nowProgressType.value}에서 ${title}을 ${actionType.value}합니다"
//            }
//            ActionType.MOVE -> {
//                "${title}을 ${prevProgressType?.value}에서 ${nowProgressType.value}로 ${actionType.value}합니다"
//            }
//            ActionType.UPDATE -> {
//                "${title}을 ${actionType.value}합니다"
//            }
//        }
//    }
}
