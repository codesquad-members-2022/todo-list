package com.example.todo.ui.toDo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.common.ActionDiffCallback
import com.example.todo.common.ProgressType
import com.example.todo.common.TodoDiffCallback
import com.example.todo.model.ActionLog
import com.example.todo.model.ActionType
import com.example.todo.model.TodoItem
import com.example.todo.ui.action.ActionAdapter
import java.time.LocalDateTime

class ToDoActivity : AppCompatActivity() {
    private lateinit var todoRecyclerView: RecyclerView
    private lateinit var inProgressRecyclerView: RecyclerView
    private lateinit var doneRecyclerView: RecyclerView
    lateinit var actionLogRecyclerView: RecyclerView

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var inProgressAdapter: TodoAdapter
    private lateinit var doneAdapter: TodoAdapter
    private lateinit var actionAdapter: ActionAdapter

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        todoRecyclerView = findViewById(R.id.rv_todo)
        inProgressRecyclerView = findViewById(R.id.rv_in_progress)
        doneRecyclerView = findViewById(R.id.rv_done)

        actionLogRecyclerView = findViewById(R.id.rv_action_log)
        val toolbar = findViewById<Toolbar>(R.id.tb_main)
        drawerLayout = findViewById(R.id.draw)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initializeTodoRecyclerViews()
        addDummyDataInRecyclerView()
    }


    private fun addDummyDataInRecyclerView() {
        val todoList = mutableListOf<TodoItem>()
        val inProgressList = mutableListOf<TodoItem>()
        val doneList = mutableListOf<TodoItem>()
        val actionList = mutableListOf<ActionLog>()

        val todo1 = TodoItem("one", "title", "content", ProgressType.TO_DO)
        val todo2 = TodoItem("two", "title2", "content2\ncontentcontent", ProgressType.TO_DO)
        val todo3 = TodoItem(
            "two", "title2", "content2\ncontentcontent\n sdfsd", ProgressType.TO_DO
        )

        val action1 = ActionLog("one", ActionType.ADD, "2022-04-07 12:00:01", ProgressType.TO_DO)
        val action2 = ActionLog("one", ActionType.ADD, "2022-04-07 10:00:01", ProgressType.TO_DO)
        val action3 = ActionLog("one", ActionType.ADD, "2022-04-05 09:00:01", ProgressType.TO_DO)

        todoList.addAll(mutableListOf(todo1, todo2, todo3))
        inProgressList.addAll(mutableListOf(todo2, todo3, todo1))
        doneList.addAll(mutableListOf(todo1, todo3, todo1))
        actionList.addAll(mutableListOf(action1, action2, action3))

        todoAdapter.submitList(todoList)
        inProgressAdapter.submitList(inProgressList)
        doneAdapter.submitList(doneList)
        actionAdapter.submitList(actionList)
    }

    private fun initializeTodoRecyclerViews() {
        todoAdapter = TodoAdapter(TodoDiffCallback())
        inProgressAdapter = TodoAdapter(TodoDiffCallback())
        doneAdapter = TodoAdapter(TodoDiffCallback())
        actionAdapter = ActionAdapter(ActionDiffCallback())

        todoRecyclerView.adapter = todoAdapter
        inProgressRecyclerView.adapter = inProgressAdapter
        doneRecyclerView.adapter = doneAdapter
        actionLogRecyclerView.adapter = actionAdapter
        //actionAdapter.notifyDataSetChanged()
        todoRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        inProgressRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        doneRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        actionLogRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu -> {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TAG = "ToDoActivity"
    }
}