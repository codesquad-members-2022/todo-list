package com.example.todolist.ui.common

enum class ActionStatus (
    val status: String,
    val korStatus: String
    ){
    NONE("NONE", "N/A"), CREATE("CREATE", "등록"), DELETE("DELETE", "삭제"), MOVE("MOVE", "이동")
}