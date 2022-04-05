package com.example.todo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.common.TodoDiffCallback
import com.example.todo.model.TodoItem

class MainActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = TodoAdapter(TodoDiffCallback())
        rv = findViewById(R.id.rv)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val list = mutableListOf<TodoItem>()
        list.add(
            TodoItem(
                "one", "title", "content"
            )
        )
        list.add(
            TodoItem(
                "two", "title2", "content2\ncontentcontent"
            )
        )
        list.add(
            TodoItem(
                "two", "title2", "content2\ncontentcontent\n sdfsd"
            )
        )

        adapter.submitList(list)
    }
}