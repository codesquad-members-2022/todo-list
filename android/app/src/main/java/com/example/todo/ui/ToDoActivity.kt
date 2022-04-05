package com.example.todo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.common.TodoDiffCallback
import com.example.todo.model.TodoItem

class ToDoActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        val toolbar= findViewById<Toolbar>(R.id.tb_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)


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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_action, menu)
        return true
    }
}