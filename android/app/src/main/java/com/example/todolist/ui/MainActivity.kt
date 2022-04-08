package com.example.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: ViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val historyAdapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        onDrawerEvent()

        binding.rvHistory.adapter = historyAdapter
        viewModel.history.observe(this) { histories ->
            historyAdapter.submitList(histories)
        }

        binding.includeTodo?.btnTodoAdd?.setOnClickListener {
            val dialog = Dialog()
            dialog.show(supportFragmentManager, "Dialog")
        }
    }

    private fun onDrawerEvent() {
        binding.btnDrawer.setOnClickListener {
            viewModel.loadDummyData()
            binding.dlDrawer.openDrawer(Gravity.RIGHT)
        }

        binding.btnClose.setOnClickListener {
            binding.dlDrawer.closeDrawer(Gravity.RIGHT)
        }
    }
}
