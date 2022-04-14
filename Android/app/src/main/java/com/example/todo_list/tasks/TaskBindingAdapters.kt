package com.example.todo_list.tasks

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setTitle")
fun setTitle(view: TextView, title: String) {
    view.text = title
}

@BindingAdapter("setContent")
fun setContent(view: TextView, content: String) {
    view.text = content
}

@BindingAdapter("setUser")
fun setUser(view: TextView, user: String) {
    view.text = user
}