package com.example.todolist.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.ui.common.ActionStatus
import com.example.todolist.ui.common.CardTouchHelper
import com.example.todolist.ui.adapter.CardAdapter
import com.example.todolist.ui.adapter.HistoryAdapter
import com.example.todolist.ui.common.CardStatus

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val cardViewModel: CardViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoAdapter = CardAdapter(cardViewModel)
        binding.rvTodo.adapter = todoAdapter
        val ongoingAdapter = CardAdapter(cardViewModel)
        binding.rvOngoing.adapter = ongoingAdapter
        val completedAdapter = CardAdapter(cardViewModel)
        binding.rvCompleted.adapter = completedAdapter
        val historyAdapter = HistoryAdapter()
        binding.rvHistory?.adapter = historyAdapter

        setOnClickMenu()
        setOnClickTodoAdd()

        val swipeHelperCallback = CardTouchHelper()
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvTodo)
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvOngoing)
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvCompleted)

        cardViewModel.todoCardList.observe(this) {
            todoAdapter.submitList(it.toList()) {
                if (cardViewModel.actionStatus == ActionStatus.CREATE) {
                    binding.rvTodo.smoothScrollToPosition(0)
                }
                binding.tvTodoBadge.text = it.size.toString()
            }
        }

        cardViewModel.ongoingCardList.observe(this) {
            ongoingAdapter.submitList(it.toList()) {
                if (cardViewModel.actionStatus == ActionStatus.CREATE) {
                    binding.rvOngoing.smoothScrollToPosition(0)
                }
                binding.tvOngoingBadge.text = it.size.toString()
            }
        }

        cardViewModel.completedCardList.observe(this) {
            completedAdapter.submitList(it.toList()) {
                if (cardViewModel.actionStatus == ActionStatus.CREATE) {
                    binding.rvCompleted.smoothScrollToPosition(0)
                }
                binding.tvCompletedBadge.text = it.size.toString()
            }
        }

        cardViewModel.error.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        historyViewModel.historyList.observe(this) {
            historyAdapter.submitList(it.toList()) {
                binding.rvHistory?.smoothScrollToPosition(0)
            }
        }
    }

    override fun onBackPressed() {
        if (binding.dloAppbar.isDrawerOpen(GravityCompat.END)) {
            binding.dloAppbar.closeDrawers()
        } else {
            super.onBackPressed()
        }
    }

    private fun setOnClickTodoAdd() {
        binding.btnTodoAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, CardStatus.TODO.status)
        }

        binding.btnOngoingAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, CardStatus.ONGOING.status)
        }

        binding.btnCompletedAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, CardStatus.COMPLETED.status)
        }
    }

    private fun setOnClickMenu() {
        binding.topAppbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.log -> {
                    historyViewModel.loadHistoryList()
                    if (!binding.dloAppbar.isDrawerOpen(GravityCompat.END)) {
                        binding.dloAppbar.openDrawer(GravityCompat.END)
                    }
                }
            }
            true
        }
    }

}