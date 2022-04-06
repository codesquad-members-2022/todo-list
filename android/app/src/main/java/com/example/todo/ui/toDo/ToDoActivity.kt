package com.example.todo.ui.toDo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.common.TodoDiffCallback
import com.example.todo.model.TodoItem
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId

class ToDoActivity : AppCompatActivity() {
    lateinit var rv: RecyclerView
    lateinit var rv2:RecyclerView
    lateinit var draw:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        val toolbar= findViewById<Toolbar>(R.id.tb_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        draw= findViewById(R.id.draw)

        val adapter = TodoAdapter(TodoDiffCallback())
        rv = findViewById(R.id.rv)
        rv2= findViewById(R.id.rv2)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv2.adapter=adapter
        rv2.layoutManager=  LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_menu-> {
                draw.openDrawer(GravityCompat.END)
            }

        }

        return super.onOptionsItemSelected(item)
    }
}