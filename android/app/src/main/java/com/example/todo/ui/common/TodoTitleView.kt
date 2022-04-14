package com.example.todo.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button

import android.widget.LinearLayout
import android.widget.TextView
import com.example.todo.R
import com.example.todo.databinding.TodoTitleViewBinding


class TodoTitleView(context: Context, attrs: AttributeSet) :
    LinearLayout(context, attrs) {
    lateinit var title: TextView
    lateinit var count: TextView
    lateinit var addButton: Button

    init {
        LayoutInflater.from(context).inflate(R.layout.todo_title_view, this, true)
        title = findViewById(R.id.tv_todo_title)
        count = findViewById(R.id.tv_content_count)
        addButton = findViewById(R.id.btn_add)
    }


}