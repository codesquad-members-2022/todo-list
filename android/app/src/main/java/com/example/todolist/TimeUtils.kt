package com.example.todolist.ui.common

import java.util.*

enum class TimeValue(val value: Int, val maximum: Int, val message: String) {
    SEC(60, 60, "분 전"),
    MIN(60, 24, "시간 전"),
    HOUR(24, 30, "일 전"),
    DAY(30, 12, "달 전"),
    MONTH(12, Int.MAX_VALUE, "년 전")
}

fun calculateTime(date: Date): String? {
    val currentTime = System.currentTimeMillis()
    val registerTime = date.time
    var differenceTime = (currentTime - registerTime) / 1000

    if (differenceTime < TimeValue.SEC.value) {
        return "${differenceTime}초 전"
    } else {
        for (timeValue in TimeValue.values()) {
            differenceTime /= timeValue.value
            if (differenceTime < timeValue.maximum) {
                return "${differenceTime}${timeValue.message}"
            }
        }
    }
    return null
}