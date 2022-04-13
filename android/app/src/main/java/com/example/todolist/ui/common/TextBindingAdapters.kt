package com.example.todolist.ui.common

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.R
import com.example.todolist.common.htmlToString
import com.example.todolist.model.ActionType.*
import com.example.todolist.model.History
import java.text.SimpleDateFormat

@BindingAdapter("formatting")
fun stringFormat(view: TextView, history: History) {
    val (_, action, title, nowStatus, beforeStatus, _) = history
    when (action) {
        ADD -> view.text =
            view.context.getString(R.string.action_default, nowStatus, title, "등록").htmlToString()
        REMOVE -> view.text =
            view.context.getString(R.string.action_default, nowStatus, title, "삭제").htmlToString()
        UPDATE -> view.text =
            view.context.getString(R.string.action_default, nowStatus, title, "변경").htmlToString()
        MOVE -> view.text = view.context.getString(
            R.string.action_move,
            title,
            beforeStatus,
            nowStatus,
            "이동"
        ).htmlToString()
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