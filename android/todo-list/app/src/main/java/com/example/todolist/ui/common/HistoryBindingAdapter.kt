package com.example.todolist.ui.common

import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.format.DateUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.data.History
import com.example.todolist.ui.common.ActionStatus.*
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("textBinding")
fun bindActivityHistoryText(view: TextView, history: History) {
    view.text = when (history.action ?: "none") {
        CREATE.status -> {
            val text =
                "<b>${convertEngActionToKorAction(history.currentCardStatus)}</b> 에 <b>${history.cardSubject}</b> 을/를 <b>${CREATE.korStatus}</b>하였습니다."
            Html.fromHtml(text, FROM_HTML_MODE_LEGACY)
        }
        DELETE.status -> {
            val text =
                "<b>${convertEngActionToKorAction(history.currentCardStatus)}</b> 에 <b>${history.cardSubject}</b> 을/를 <b>${DELETE.korStatus}</b>하였습니다."
            Html.fromHtml(text, FROM_HTML_MODE_LEGACY)
        }
        MOVE.status -> {
            val text =
                "<b>${history.cardSubject}</b> 을/를 <b>${convertEngActionToKorAction(history.prevCardStatus)}</b> 에서 <b>${convertEngActionToKorAction(history.currentCardStatus)}</b> 으로 <b>${MOVE.korStatus}</b>하였습니다."
            Html.fromHtml(text, FROM_HTML_MODE_LEGACY)
        }
        else -> "아무행동도 하지 않으셨습니다."
    }
}

private fun convertEngActionToKorAction(action: String?): String {
    return when (action) {
        "todo" -> CardStatus.TODO.korStatus
        "ongoing" -> CardStatus.ONGOING.korStatus
        "completed" -> CardStatus.COMPLETED.korStatus
        else -> "알 수 없음"
    }
}

@BindingAdapter("timeStamp")
fun bindTimeStamp(view: TextView, timestamp: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA)
    val date: Date? = inputFormat.parse(timestamp)

    if (date == null) view.text = "알 수 없음"
    else {
        val ago = DateUtils.getRelativeTimeSpanString(
            date.time,
            Calendar.getInstance().timeInMillis,
            DateUtils.MINUTE_IN_MILLIS
        )
        view.text = ago.toString()
    }
}