package com.example.todo.model

import com.example.todo.common.ProgressType
import java.time.LocalDateTime

enum class ActionType(val value:String){
    ADD("등록"),REMOVE("삭제"),MOVE("이동"),UPDATE("수정"),
}


data class ActionLog(val title:String, val actionType:ActionType, val time:LocalDateTime , val nowProgressType:ProgressType, val prevProgressType: ProgressType?=null   ){

    override fun toString(): String {
        return when(actionType){
            ActionType.ADD ->{
              "${nowProgressType.value}에 ${title}을 ${actionType.value}합니다"
            }
            ActionType.REMOVE->{
                "${nowProgressType.value}에서 ${title}을 ${actionType.value}합니다"
            }

            ActionType.MOVE->{
                "${title}을 ${prevProgressType?.value}에서 ${nowProgressType.value}로 ${actionType.value}합니다"
            }
            ActionType.UPDATE->{
                "${title}을 ${actionType.value}합니다"
            }


        }
    }
}
