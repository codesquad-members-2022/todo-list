package com.example.todo_list.history

import android.text.format.DateUtils
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.databinding.BindingAdapter
import com.example.todo_list.R
import com.example.todo_list.history.data.HistoryCard
import java.text.SimpleDateFormat
import java.util.*

private fun convertAction(action: String): String {
    return when (action) {
        "move" -> "이동"
        "update" -> "갱신"
        "remove" -> "삭제"
        else -> "추가"
    }
}

private fun convertStatus(status: String): String {
    return when (status) {
        "todo" -> "해야할 일"
        "doing" -> "하고 있는 일"
        else -> "한 일"
    }
}

@BindingAdapter("setUsername") // 히스토리 item xml과 바인딩
fun setUsername(view: TextView, username: String) {
    view.text = String.format("@%s", username)
}

@BindingAdapter("setBody") // 히스토리 item xml과 바인딩
fun setBody(view: TextView, body: HistoryCard) {
    view.text = when (body.action) {
        "move" ->
            HtmlCompat.fromHtml(
                view.context.getString(
                    R.string.history_move_string,
                    body.todo.title,
                    convertStatus(body.from_status),
                    convertStatus(body.to_status),
                    convertAction(body.action)
                ),
                FROM_HTML_MODE_LEGACY
            )
        "remove" ->
            HtmlCompat.fromHtml(
                view.context.getString(
                    R.string.history_remove_string,
                    body.todo.title,
                    convertStatus(body.from_status),
                    convertAction(body.action)
                ),
                FROM_HTML_MODE_LEGACY
            )
        "update" ->
            HtmlCompat.fromHtml(
                view.context.getString(
                    R.string.history_update_string,
                    body.todo.title,
                    body.todo.title,
                    convertStatus(body.from_status),
                    convertAction(body.action)
                ),
                FROM_HTML_MODE_LEGACY
            )
        else ->
            HtmlCompat.fromHtml(
                view.context.getString(
                    R.string.history_add_string,
                    convertStatus(body.todo.status),
                    body.todo.title,
                    convertAction(body.action)
                ),
                FROM_HTML_MODE_LEGACY
            )
    }
}

@BindingAdapter("setTimeStamp") // 히스토리 item xml과 바인딩
fun setTimeStamp(view: TextView, timestamp: String) {
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
