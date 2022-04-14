package com.example.todolist.ui.common

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todolist.R
import com.example.todolist.common.htmlToSpanned
import com.example.todolist.model.ActionType.*
import com.example.todolist.model.History
import java.text.SimpleDateFormat

@BindingAdapter("formatting")
fun stringFormat(view: TextView, history: History) {
    val (_, action, title, nowStatus, beforeStatus, _) = history
    val now = when (nowStatus) {
        "TODO" -> "해야할 일"
        "IN_PROGRESS" -> "하고 있는 일"
        else -> "완료한 일"
    }
    val before = when (beforeStatus) {
        "TODO" -> "해야할 일"
        "IN_PROGRESS" -> "하고 있는 일"
        else -> "완료한 일"
    }

    when (action) {
        ADD -> view.text =
            view.context.getString(R.string.action_default, now, title, "등록").htmlToSpanned()
        REMOVE -> view.text =
            view.context.getString(R.string.action_default, now, title, "삭제").htmlToSpanned()
        UPDATE -> view.text =
            view.context.getString(R.string.action_default, now, title, "변경").htmlToSpanned()
        MOVE -> view.text =
            view.context.getString(R.string.action_move, title, before, now, "이동").htmlToSpanned()
    }
}

@SuppressLint("SimpleDateFormat")
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