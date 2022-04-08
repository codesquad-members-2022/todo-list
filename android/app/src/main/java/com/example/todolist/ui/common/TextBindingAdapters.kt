package com.example.todolist.ui.common

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.R
import com.example.todolist.model.ActionType.*
import com.example.todolist.model.History
import java.text.SimpleDateFormat

@BindingAdapter("formatting")
fun stringFormat(view: TextView, history: History) {
    val (_, action, title, nowStatus, beforeStatus, _) = history
    when (action) {
        ADD -> view.text =
            Html.fromHtml(view.context.getString(R.string.action_default, nowStatus, title, "등록"))
        REMOVE -> view.text =
            Html.fromHtml(view.context.getString(R.string.action_default, nowStatus, title, "삭제"))
        UPDATE -> view.text =
            Html.fromHtml(view.context.getString(R.string.action_default, nowStatus, title, "변경"))
        MOVE -> view.text = Html.fromHtml(view.context.getString(R.string.action_move,
            title,
            beforeStatus,
            nowStatus,
            "이동"))
    }
}

@BindingAdapter("formattingDate")
fun dateFormat(view: TextView, stringDate: String) {
    val format = SimpleDateFormat(view.context.getString(R.string.date_format))
    val date = format.parse(stringDate)
    val result = date?.let { calculateTime(date) } ?: ""
    view.text = result
}

@BindingAdapter("author")
fun authorFormat(view: TextView, author: String) {
    view.text = view.context.getString(R.string.author, author)
}