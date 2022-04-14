package com.example.todolist.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.ui.common.ActionStatus
import com.example.todolist.ui.common.TodoTouchHelper
import com.example.todolist.ui.adapter.TodoAdapter
import com.example.todolist.ui.common.CardStatus

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val viewModel: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoAdapter = TodoAdapter(viewModel)
        binding.rvTodo.adapter = todoAdapter
        val ongoingAdapter = TodoAdapter(viewModel)
        binding.rvProgress.adapter = ongoingAdapter
        val complete = TodoAdapter(viewModel)
        binding.rvDone.adapter = complete

        setOnClickMenu()
        setOnClickTodoAdd()

        val swipeHelperCallback = TodoTouchHelper()
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvTodo)
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvProgress)
        ItemTouchHelper(swipeHelperCallback).attachToRecyclerView(binding.rvDone)

        viewModel.todoCardList.observe(this) {
            todoAdapter.submitList(it.toList()) {
                if (viewModel.actionStatus == ActionStatus.ADD) {
                    binding.rvTodo.smoothScrollToPosition(0)
                }
                binding.tvTodoBadge.text = it.size.toString()
            }
        }

        viewModel.ongoingCardList.observe(this) {
            ongoingAdapter.submitList(it.toList()) {
                if (viewModel.actionStatus == ActionStatus.ADD) {
                    binding.rvProgress.smoothScrollToPosition(0)
                }
                binding.tvProgressBadge.text = it.size.toString()
            }
        }

        viewModel.completedCardList.observe(this) {
            complete.submitList(it.toList()) {
                if (viewModel.actionStatus == ActionStatus.ADD) {
                    binding.rvDone.smoothScrollToPosition(0)
                }
                binding.tvDoneBadge.text = it.size.toString()
            }

        }

        viewModel.error.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
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

        binding.btnProgressAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, CardStatus.ONGOING.status)
        }

        binding.btnDoneAdd.setOnClickListener {
            val dialog = CreateCardDialogFragment()
            dialog.show(supportFragmentManager, CardStatus.COMPLETE.status)
        }
    }

    private fun setOnClickMenu() {
        binding.topAppbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.log -> {
                    if (!binding.dloAppbar.isDrawerOpen(GravityCompat.END)) {
                        binding.dloAppbar.openDrawer(GravityCompat.END)
                    }
                }
            }
            true
        }
    }

}