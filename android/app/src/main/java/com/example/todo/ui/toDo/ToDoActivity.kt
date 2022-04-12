package com.example.todo.ui.toDo

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.ui.action.ActionDiffCallback

import com.example.todo.databinding.ActivityTodoBinding
import com.example.todo.model.*

import com.example.todo.ui.action.ActionAdapter
import com.example.todo.ui.common.ViewModelFactory

class ToDoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoBinding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var inProgressAdapter: TodoAdapter
    private lateinit var doneAdapter: TodoAdapter
    private lateinit var actionAdapter: ActionAdapter
    private val toDoViewModel: ToDoViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo)
        setToolBar()
        initializeRecyclerViews()
        addDummyDataInRecyclerView()

    }

    private fun setToolBar() {
        setSupportActionBar(binding.tbMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_menu -> {
                binding.draw.openDrawer(GravityCompat.END)
            }
        }
        return super.onOptionsItemSelected(item)
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

        val action1 = ActionLog(
            "one",
            ActionType.ADD,
            "2022-04-07 12:00:01",
            ProgressType.TO_DO,
            ProgressType.IN_PROGRESS
        )
        val action2 = ActionLog(
            "one",
            ActionType.ADD,
            "2022-04-07 10:00:01",
            ProgressType.TO_DO,
            ProgressType.IN_PROGRESS
        )
        val action3 = ActionLog(
            "one",
            ActionType.ADD,
            "2022-04-05 09:00:01",
            ProgressType.TO_DO,
            ProgressType.IN_PROGRESS
        )

        todoList.addAll(mutableListOf(todo1, todo2, todo3))
        inProgressList.addAll(mutableListOf(todo2, todo3, todo1))
        doneList.addAll(mutableListOf(todo1, todo3, todo1))
        actionList.addAll(mutableListOf(action1, action2, action3))

        todoAdapter.submitList(todoList)
        inProgressAdapter.submitList(inProgressList)
        doneAdapter.submitList(doneList)
        actionAdapter.submitList(actionList)
    }

    private fun initializeRecyclerViews() {
        todoAdapter = TodoAdapter(TodoDiffCallback())
        inProgressAdapter = TodoAdapter(TodoDiffCallback())
        doneAdapter = TodoAdapter(TodoDiffCallback())
        actionAdapter = ActionAdapter(ActionDiffCallback())

        binding.rvTodo.adapter = todoAdapter
        binding.rvInProgress.adapter = inProgressAdapter
        binding.rvDone.adapter = doneAdapter
        binding.rvActionLog.adapter = actionAdapter
        binding.rvTodo.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvInProgress.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvDone.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvActionLog.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.todoTitleViewTodo.title.text = "해야할 일"
        binding.todoTitleViewInProgress.title.text = "하고있는 일"
        binding.todoTitleViewTodoDone.title.text = "완료한 일"

        toDoViewModel.todoList.observe(this) {
            todoAdapter.submitList(it)
            binding.todoTitleViewTodo.count.text = it.size.toString()
        }

        toDoViewModel.inProgressList.observe(this) {
            inProgressAdapter.submitList(it)
            binding.todoTitleViewInProgress.count.text = (it.size).toString()
        }

        toDoViewModel.doneList.observe(this) {
            doneAdapter.submitList(it)
            binding.todoTitleViewTodoDone.count.text = (it.size).toString()
        }

        toDoViewModel.actionList.observe(this) {
            actionAdapter.submitList(it)
            // 테스트용
            binding.todoTitleViewTodo.count.text = it.size.toString()
        }

    }

    companion object {
        const val TAG = "ToDoActivity"
    }
}