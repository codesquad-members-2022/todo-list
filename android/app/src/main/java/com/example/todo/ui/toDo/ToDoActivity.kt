package com.example.todo.ui.toDo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.ui.action.ActionDiffCallback

import com.example.todo.databinding.ActivityTodoBinding
import com.example.todo.model.*

import com.example.todo.ui.action.ActionAdapter
import com.example.todo.ui.common.ToDoMoveListener
import com.example.todo.ui.common.ToDoTouchHelper
import com.example.todo.ui.common.ViewModelFactory

class ToDoActivity : AppCompatActivity(), TodoAdapter.UpdateDialogListener, ToDoMoveListener {

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
        initializeTodoTitleViews()
    }

    private fun initializeTodoTitleViews() {
        binding.todoTitleViewTodo.title.text = resources.getString(R.string.todo_title)
        binding.todoTitleViewInProgress.title.text = resources.getString(R.string.in_progress_title)
        binding.todoTitleViewTodoDone.title.text = resources.getString(R.string.done_title)
        binding.todoTitleViewTodo.addButton.setOnClickListener {
            val addDialog = ToDoDialog(ProgressType.TO_DO)
            addDialog.show(supportFragmentManager, "todoAddDialog")
        }
        binding.todoTitleViewInProgress.addButton.setOnClickListener {
            val addDialog = ToDoDialog(ProgressType.IN_PROGRESS)
            addDialog.show(supportFragmentManager, "inProgressAddDialog")
        }
        binding.todoTitleViewTodoDone.addButton.setOnClickListener {
            val addDialog = ToDoDialog(ProgressType.DONE)
            addDialog.show(supportFragmentManager, "doneAddDialog")
        }
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

    private fun initializeRecyclerViews() {
        todoAdapter = TodoAdapter(this, this, this, toDoViewModel, TodoDiffCallback())
        inProgressAdapter = TodoAdapter(this, this, this, toDoViewModel, TodoDiffCallback())
        doneAdapter = TodoAdapter(this, this, this, toDoViewModel, TodoDiffCallback())
        actionAdapter = ActionAdapter(ActionDiffCallback())

        ItemTouchHelper(ToDoTouchHelper()).attachToRecyclerView(binding.rvTodo)
        ItemTouchHelper(ToDoTouchHelper()).attachToRecyclerView(binding.rvInProgress)
        ItemTouchHelper(ToDoTouchHelper()).attachToRecyclerView(binding.rvDone)

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

        toDoViewModel.todoList.observe(this) {
            todoAdapter.submitList(it)
            binding.todoTitleViewTodo.count.text = it.size.toString()
        }

        toDoViewModel.inProgressList.observe(this) {
            inProgressAdapter.submitList(it)

            binding.todoTitleViewInProgress.count.text = it.size.toString()
        }

        toDoViewModel.doneList.observe(this) {
            doneAdapter.submitList(it)
            binding.todoTitleViewTodoDone.count.text = it.size.toString()
        }

        toDoViewModel.actionList.observe(this) {
            actionAdapter.submitList(it)
            // 테스트용
            // binding.todoTitleViewTodo.count.text = it?.size.toString()
        }
        binding.rvTodo.setOnDragListener(todoAdapter.dragInstance)
        binding.rvInProgress.setOnDragListener(inProgressAdapter.dragInstance)
        binding.rvDone.setOnDragListener(doneAdapter.dragInstance)
    }

    companion object {
        const val TAG = "ToDoActivity"
    }

    override fun updateDialog(item: TodoItem) {
        UpdateToDoDialog(item).show(supportFragmentManager, "update")
    }

    override fun moveData(
        rvType: ProgressType,
        prevItemId: Int?,
        nextItemId: Int?,
        moveItemId: Int?
    ) {
//        if (moveItemId != null) {
//            toDoViewModel.moveTodoItem(moveItemId,rvType,prevItemId,nextItemId)
//        }
    }


}