package com.codesquad.aos.todolist.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.common.ViewModelFactory
import com.codesquad.aos.todolist.common.utils.VerticalItemDecorator
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.databinding.ActivityMainBinding
import com.codesquad.aos.todolist.ui.adapter.LogCardListAdapter
import com.codesquad.aos.todolist.ui.adapter.LogPagingAdapter
import com.codesquad.aos.todolist.ui.adapter.TodoCardListAdapter
import com.codesquad.aos.todolist.ui.dialog.CompleteDialogFragment
import com.codesquad.aos.todolist.ui.dialog.ProgressDialogFragment
import com.codesquad.aos.todolist.ui.dialog.TodoDialogFragment
import com.codesquad.aos.todolist.ui.dialog.edit.CardEditDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.min

class MainActivity : AppCompatActivity(), DataChangeListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var todoCardListAdapter: TodoCardListAdapter
    private lateinit var progressCardListAdapter: TodoCardListAdapter
    private lateinit var completeCardListAdapter: TodoCardListAdapter

    private lateinit var logCardListAdapter: LogCardListAdapter
    private lateinit var logPaingAdapter: LogPagingAdapter

    private val viewModel: TodoViewModel by viewModels { ViewModelFactory(this) }
    private val dragListener = DragListener(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setMenuClick()
        setDrawerListener()
        setDrawerClose()
        setViewModel()

        setDialogFragmentView()
        setTodoRecyclerView()
        setProgressRecyclerView()
        setCompleteRecyclerView()

        setLogRecyclerView()

        setProgressBar()

        // 시작 시 전체 데이터 가져오기
        viewModel.getCardItems()
    }

    // 할 일 전용
    private fun setTodoRecyclerView() {
        todoCardListAdapter = TodoCardListAdapter(
            { cardId ->
                viewModel.deleteCard(cardId)
            }, this, {
                // CardEidtDialog를 열면서 Card 객체(it) 전달하기
                val args = Bundle()
                args.putString("title", it.title)
                args.putInt("id", it.cardId)
                args.putString("section", it.section)
                args.putString("content", it.content)
                args.putInt("order", it.order)

                val cardEditDialogFragment = CardEditDialogFragment()
                cardEditDialogFragment.arguments = args
                cardEditDialogFragment.show(supportFragmentManager, "EditDialog")
            }, {
                val preOrder = -1
                val nextOrder = completeCardListAdapter.getFirstElementOrder()
                val cardId = it
                val prevSection = "todo"
                val nextSection = "done"

                viewModel.moveCard(cardId, nextOrder, preOrder, nextSection, prevSection)
            })

        binding.rvTodo.adapter = todoCardListAdapter
        binding.rvTodo.layoutManager = LinearLayoutManager(this)
        binding.rvTodo.addItemDecoration(VerticalItemDecorator(15))

        val touchHelper = TodoTouchHelper(todoCardListAdapter).apply {
            setClamp(170f)  // 170 이
        }

        binding.rvTodo.setOnDragListener(todoCardListAdapter.dragInstance)

        ItemTouchHelper(touchHelper).attachToRecyclerView(binding.rvTodo)

        binding.rvTodo.setOnTouchListener { _, _ ->
            touchHelper.removePreviousClamp(binding.rvTodo)
            false
        }

        registerForContextMenu(binding.rvTodo)
        // 더미데이터
        //viewModel.addTodo("rvTODO", "TAG 1")
        //viewModel.addTodo("rvTODO", "TAG 2")
    }

    private fun setProgressRecyclerView() {
        progressCardListAdapter = TodoCardListAdapter(
            { cardId ->
                viewModel.deleteCard(cardId)
            }, this, {
                val args = Bundle()
                args.putString("title", it.title)
                args.putInt("id", it.cardId)
                args.putString("section", it.section)
                args.putString("content", it.content)
                args.putInt("order", it.order)

                val cardEditDialogFragment = CardEditDialogFragment()
                cardEditDialogFragment.arguments = args
                cardEditDialogFragment.show(supportFragmentManager, "EditDialog")
            },  {
                val preOrder = -1
                val nextOrder = completeCardListAdapter.getFirstElementOrder()
                val cardId = it
                val prevSection = "doing"
                val nextSection = "done"

                viewModel.moveCard(cardId, nextOrder, preOrder, nextSection, prevSection)
            })

        binding.rvProgress.adapter = progressCardListAdapter
        binding.rvProgress.layoutManager = LinearLayoutManager(this)
        binding.rvProgress.addItemDecoration(VerticalItemDecorator(15))

        binding.rvProgress.setOnDragListener(progressCardListAdapter.dragInstance)

        val touchHelper = TodoTouchHelper(progressCardListAdapter).apply {
            setClamp(170f)  // 170 이
        }

        ItemTouchHelper(touchHelper).attachToRecyclerView(binding.rvProgress)

        binding.rvProgress.setOnTouchListener { _, _ ->
            touchHelper.removePreviousClamp(binding.rvProgress)
            false
        }

        // 더미데이터
        //viewModel.addProgress("rvProgress", "TAG 1")
    }

    private fun setCompleteRecyclerView() {
        completeCardListAdapter = TodoCardListAdapter(
            { cardId ->
                viewModel.deleteCard(cardId)
            }, this, {
                val args = Bundle()
                args.putString("title", it.title)
                args.putInt("id", it.cardId)
                args.putString("section", it.section)
                args.putString("content", it.content)
                args.putInt("order", it.order)

                val cardEditDialogFragment = CardEditDialogFragment()
                cardEditDialogFragment.arguments = args
                cardEditDialogFragment.show(supportFragmentManager, "EditDialog")
            }, {
                Log.d("AppTest", "이미 '완료한 일' 목록에 위치하고 있습니다")
            })

        binding.rvComplete.adapter = completeCardListAdapter
        binding.rvComplete.layoutManager = LinearLayoutManager(this)
        binding.rvComplete.addItemDecoration(VerticalItemDecorator(15))

        binding.rvComplete.setOnDragListener(completeCardListAdapter.dragInstance)

        val touchHelper = TodoTouchHelper(completeCardListAdapter).apply {
            setClamp(170f)  // 170 이
        }

        ItemTouchHelper(touchHelper).attachToRecyclerView(binding.rvComplete)

        binding.rvComplete.setOnTouchListener { _, _ ->
            touchHelper.removePreviousClamp(binding.rvComplete)
            false
        }

        // 더미데이터
        //viewModel.addComplete("rvComplete", "TAG 1")
    }

    private fun setLogRecyclerView() {
        logCardListAdapter = LogCardListAdapter()
        logPaingAdapter = LogPagingAdapter()

        binding.rvLog.adapter = logPaingAdapter
        binding.rvLog.layoutManager = LinearLayoutManager(this)

        // 로딩 상태
        logPaingAdapter.addLoadStateListener { combinedLoadStates ->
            binding.progressBarLog?.isVisible = combinedLoadStates.source.refresh is LoadState.Loading

            if(combinedLoadStates.source.refresh is LoadState.Error){
                Log.d("AppTest", "loading error")
            }
        }

        lifecycleScope.launch {
            viewModel.logFlow
                .collectLatest { logX ->
                    logPaingAdapter.submitData(logX)
                }
        }

       /* viewModel.addLog("오늘 할일을 추가했습니다", "1분전")
        viewModel.addLog("오늘 할일을 추가했습니다", "2분전")
        viewModel.addLog("오늘 할일을 추가했습니다", "3분전")*/
    }


    private fun setViewModel() {
        viewModel.todoListLd.observe(this) {
            binding.tvTodoBadgeCount.text = it.size.toString()
            todoCardListAdapter.submitList(it.toList())
        }

        viewModel.progressListLd.observe(this) {
            binding.tvProgressBadgeCount.text = it.size.toString()
            progressCardListAdapter.submitList(it.toList())
        }

        viewModel.completeListLd.observe(this) {
            binding.tvCompleteBadgeCount.text = it.size.toString()
            completeCardListAdapter.submitList(it.toList())
        }

        viewModel.LogListLd.observe(this) {
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
                viewModel.getLogs()  // 우측 드로어 열린 경우 로그 데이터 가져오기
                //binding.rvLog.scrollToPosition(0)
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

    // implement DataChangeListener
    override fun swapData(rvType: Int, cardId: Int, nextOrder: Int, preOrder: Int, nextSection: String, prevSection: String) {
        when (rvType) {
            1 -> viewModel.moveCard(cardId, nextOrder, preOrder, nextSection, prevSection)
            2 -> viewModel.moveCard(cardId, nextOrder, preOrder, nextSection, prevSection)
            3 -> viewModel.moveCard(cardId, nextOrder, preOrder, nextSection, prevSection)
        }
    }

    /*override fun addDataAtEnd(rvType: Int, cardId: Int, nextOrder: Int, preOrder: Int, nextSection: String, prevSection: String) {
        when (rvType) {
            1 -> viewModel.addTodoCard(card)
            2 -> viewModel.addProgressCard(card)
            3 -> viewModel.addCompleteCard(card)
        }
    }*/

    override fun addDataAtInx(rvType: Int, cardId: Int, nextOrder: Int, preOrder: Int, nextSection: String, prevSection: String) {
        when (rvType) {
            1 -> viewModel.moveCard(cardId, nextOrder, preOrder, nextSection, prevSection)
            2 -> viewModel.moveCard(cardId, nextOrder, preOrder, nextSection, prevSection)
            3 -> viewModel.moveCard(cardId, nextOrder, preOrder, nextSection, prevSection)
        }
    }

    override fun deleteData(rvType: Int, targetIndex: Int) {
        when (rvType) {
            1 -> viewModel.deleteTodo(targetIndex)
            2 -> viewModel.deleteProgress(targetIndex)
            3 -> viewModel.deleteComplete(targetIndex)
        }
    }

    fun setProgressBar() {
        viewModel.progressVisible.observe(this) {
            binding.progressBar?.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}