package com.example.todolist.ui.common

import java.util.*

const val SEC = 60
const val MIN = 60
const val HOUR = 24
const val DAY = 30
const val MONTH = 12

fun calculateTime(date: Date): String {
    val curTime = System.currentTimeMillis()
    val regTime = date.time
    var diffTime = (curTime - regTime) / 1000

    return when {
        diffTime < SEC -> "${diffTime}초 전"
        SEC.let { diffTime /= it; diffTime } < MIN -> "${diffTime}분 전"
        MIN.let { diffTime /= it; diffTime } < HOUR -> "${diffTime}시간 전"
        HOUR.let { diffTime /= it; diffTime } < DAY -> "${diffTime}일 전"
        DAY.let { diffTime /= it; diffTime } < MONTH -> "${diffTime}달 전"
        else -> "${diffTime}년 전"
    }
}