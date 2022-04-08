package com.example.todo.common

import android.os.Build
import org.joda.time.DateTimeZone
import org.joda.time.Seconds
import org.joda.time.format.DateTimeFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

const val SECONDS_OF_MINUTE = 60
const val SECONDS_OF_HOUR = 60 * 60
const val SECONDS_OF_DAY = 60 * 60 * 24

fun getTimeDiff(dateTimeInfo: String): String {
    val seconds = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val nowDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
        val dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val actionDateTime = LocalDateTime.parse(dateTimeInfo, dataFormatter)
        Duration.between(actionDateTime, nowDateTime).seconds.toInt()
    } else {
        val dataFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
        val nowDateTime = org.joda.time.LocalDateTime.now(DateTimeZone.forID("Asia/Seoul"))
        val actionDateTime = org.joda.time.LocalDateTime.parse(dateTimeInfo, dataFormatter)
        Seconds.secondsBetween(actionDateTime, nowDateTime).seconds
    }
    return secondsToDuration(seconds)
}

fun secondsToDuration(seconds: Int): String {
    return when {
        seconds > SECONDS_OF_DAY -> {
            "${seconds / SECONDS_OF_DAY}일 전"
        }
        seconds > SECONDS_OF_HOUR -> {
            "${seconds / SECONDS_OF_HOUR}시간 전"
        }
        seconds > SECONDS_OF_MINUTE -> {
            "${seconds / SECONDS_OF_MINUTE}분 전"
        }
        else -> {
            "${seconds}초 전"
        }
    }
}