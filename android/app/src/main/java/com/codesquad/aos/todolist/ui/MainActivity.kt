package com.codesquad.aos.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.common.utils.VerticalItemDecorator
import com.codesquad.aos.todolist.databinding.ActivityMainBinding
import com.codesquad.aos.todolist.ui.adapter.LogCardListAdapter
import com.codesquad.aos.todolist.ui.adapter.TodoCardListAdapter
import com.codesquad.aos.todolist.ui.dialog.CompleteDialogFragment
import com.codesquad.aos.todolist.ui.dialog.ProgressDialogFragment
import com.codesquad.aos.todolist.ui.dialog.TodoDialogFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var todoCardListAdapter: TodoCardListAdapter
    private lateinit var progressCardListAdapter: TodoCardListAdapter
    private lateinit var completeCardListAdapter: TodoCardListAdapter
    private lateinit var logCardListAdapter: LogCardListAdapter

    private val viewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setMenuClick()
        setDrawerListener()
        setDrawerClose()
        setViewModel()

        setTodoRecyclerView()
        setProgressRecyclerView()
        setCompleteRecyclerView()
        setLogRecyclerView()

        setDialogFragmentView()
    }


    private fun setTodoRecyclerView() {
        todoCardListAdapter = TodoCardListAdapter()
        binding.rvTodo.adapter = todoCardListAdapter
        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        binding.rvTodo.addItemDecoration(VerticalItemDecorator(15))

        viewModel.addTodo("hihi", "hihihi")
        viewModel.addTodo("byebye", "byebyebye")
    }

    private fun setProgressRecyclerView() {
        progressCardListAdapter = TodoCardListAdapter()
        binding.rvProgress.adapter = progressCardListAdapter
        binding.rvProgress.layoutManager = LinearLayoutManager(this)
        binding.rvProgress.addItemDecoration(VerticalItemDecorator(15))

        viewModel.addProgress("han-todo", "오늘은 조시랑 같이 코딩하기")
    }

    private fun setCompleteRecyclerView() {
        completeCardListAdapter = TodoCardListAdapter()
        binding.rvComplete.adapter = completeCardListAdapter
        binding.rvComplete.layoutManager = LinearLayoutManager(this)
        binding.rvComplete.addItemDecoration(VerticalItemDecorator(15))

        viewModel.addComplete("AOS-todo", "회의록 작성하기")
    }

    private fun setLogRecyclerView() {
        logCardListAdapter = LogCardListAdapter()
        binding.rvLog.adapter = logCardListAdapter
        binding.rvLog.layoutManager = LinearLayoutManager(this)

        viewModel.addLog("오늘 할일을 추가했습니다", "1분전")
        viewModel.addLog("오늘 할일을 추가했습니다", "2분전")
        viewModel.addLog("오늘 할일을 추가했습니다", "3분전")
    }


    private fun setViewModel() {
        viewModel.todoListId.observe(this) {
            binding.tvTodoBadgeCount.text = it.size.toString()
            todoCardListAdapter.submitList(it.toList())
        }

        viewModel.progressListId.observe(this) {
            binding.tvProgressBadgeCount.text = it.size.toString()
            progressCardListAdapter.submitList(it.toList())
        }

        viewModel.completeListId.observe(this) {
            binding.tvCompleteBadgeCount.text = it.size.toString()
            completeCardListAdapter.submitList(it.toList())
        }

        viewModel.LogListId.observe(this) {
            logCardListAdapter.submitList(it.toList())
        }
    }

    private fun setMenuClick() {
        binding.toolbarMenu.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.appbar_main_menu -> {
                    Log.d("AppTest", "appbar-menu")
                    binding.drawerLayout.openDrawer(GravityCompat.END)

                    // binding.drawerLaytout?.open()
                    // 위 방식으로 하면 안드로이드 자체에서 gravity가 left 인 드로어를 필요로 하는 것으로 이해
                    // 그래서 앱이 죽는현상 발생
                    true
                }
                else -> false
            }
        }
    }

    private fun setDrawerListener() {
        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                Log.d("AppTest", "onDrawerSlide called")
            }

            override fun onDrawerOpened(drawerView: View) {
                Log.d("AppTest", "onDrawerOpened called")

            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerStateChanged(newState: Int) {
            }
        })
    }

    private fun setDrawerClose() {
        binding.ivCloseDrawer.setOnClickListener {
            binding.drawerLayout.closeDrawer(GravityCompat.END)
            // 닫는 부분도 close()로만 하면 에러 발생
        }
    }

    private fun setDialogFragmentView() {
        binding.btnAddTodo.setOnClickListener {
            TodoDialogFragment().show(
                supportFragmentManager, "DialogFragment"
            )
        }

        binding.btnAddProgress.setOnClickListener {
            ProgressDialogFragment().show(
                supportFragmentManager, "DialogFragment"
            )
        }

        binding.btnAddComplete.setOnClickListener {
            CompleteDialogFragment().show(
                supportFragmentManager, "DialogFragment"
            )
        }
    }
}