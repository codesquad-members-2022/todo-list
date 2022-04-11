package com.example.todo_list

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo_list.databinding.ActivityMainBinding
import com.example.todo_list.history.HistoryAdapter
import com.example.todo_list.history.HistoryViewModel
import com.example.todo_list.history.data.HistoryCard
import com.example.todo_list.history.data.HistoryRepository
import com.example.todo_list.history.data.Todo
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        historyViewModel = ViewModelProvider(this, ViewModelFactory(HistoryRepository())).get(HistoryViewModel::class.java)

        val adapter = HistoryAdapter()
        // 인터넷 추천:
        // private var adapter by lazy { HistoryAdapter() }
        binding.recyclerviewHistory.adapter = adapter
        // 히스토리 리싸이클러뷰에다가 HistoryAdapter클래스를 연결시킨다.
        // 리싸이클러뷰xml은  tools:listitem 을 통해 history_item을 호출하고있다.
        // 왜 adapter 대신 HistoryAdapter()를 직접 = 해주면 작동을 안할까?
        binding.btnMenu.setOnClickListener {
            binding.mainLayout.openDrawer(GravityCompat.END)
            historyViewModel.getHistory()
        }

        binding.btnClose.setOnClickListener { binding.mainLayout.closeDrawer(GravityCompat.END) }
        binding.naviView.setNavigationItemSelectedListener(this)

        historyViewModel.historyList.observe(this) {
            adapter.submitList(it)
        }
        historyViewModel.checkLoading.observe(this) {
            if (it) {
                binding.spinner.visibility = View.VISIBLE
                binding.recyclerviewHistory.visibility = View.GONE
            } else {
                binding.spinner.visibility = View.GONE
                binding.recyclerviewHistory.visibility = View.VISIBLE
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}