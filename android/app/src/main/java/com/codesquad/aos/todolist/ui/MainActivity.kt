package com.codesquad.aos.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.common.utils.VerticalItemDecorator
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.databinding.ActivityMainBinding
import com.codesquad.aos.todolist.ui.adapter.TodoCardListAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var todoCardListAdapter: TodoCardListAdapter
    private lateinit var progressCardListAdapter: TodoCardListAdapter
    private lateinit var completeCardListAdapter: TodoCardListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setMenuClick()
        setDrawerListener()
        setDrawerClose()

        setTodoRecyclerView()
        setProgressRecyclerView()
        setCompleteRecyclerView()

        setDialogFragmentView()
    }

    fun setTodoRecyclerView() {
        todoCardListAdapter = TodoCardListAdapter()
        binding.rvTodo.adapter = todoCardListAdapter
        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        binding.rvTodo.addItemDecoration(VerticalItemDecorator(15))

        var testList = listOf(
            Card(1, "hihi", "byebye", "author by android"),
            Card(
                2,
                "zoozoo",
                "icecream icecream icecream icecream icecream icecream",
                "author by iOS"
            )
        )
        todoCardListAdapter.submitList(testList.toList())
    }

    fun setProgressRecyclerView() {
        progressCardListAdapter = TodoCardListAdapter()
        binding.rvProgress.adapter = progressCardListAdapter
        binding.rvProgress.layoutManager = LinearLayoutManager(this)
        binding.rvProgress.addItemDecoration(VerticalItemDecorator(15))

        var testList = listOf(
            Card(1, "hello josh", "byebye", "author by android"),
            Card(
                2,
                "zoozoo",
                "icecream icecream icecream icecream icecream icecream",
                "author by iOS"
            )
        )
        progressCardListAdapter.submitList(testList.toList())
    }

    fun setCompleteRecyclerView() {
        completeCardListAdapter = TodoCardListAdapter()
        binding.rvComplete.adapter = completeCardListAdapter
        binding.rvComplete.layoutManager = LinearLayoutManager(this)
        binding.rvComplete.addItemDecoration(VerticalItemDecorator(15))

        var testList = listOf(
            Card(1, "hi Han", "byebye", "author by android"),
            Card(
                2,
                "zoozoo",
                "icecream icecream icecream icecream icecream icecream",
                "author by iOS"
            )
        )
        completeCardListAdapter.submitList(testList.toList())
    }

    fun setMenuClick() {
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

    fun setDrawerListener() {
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

    fun setDrawerClose() {
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
    }
}