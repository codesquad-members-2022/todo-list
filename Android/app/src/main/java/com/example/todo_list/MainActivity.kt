package com.example.todo_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageButton
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.example.todo_list.databinding.ActivityMainBinding
import com.example.todo_list.history.data.HistoryRepository
import com.example.todo_list.model.HistoryCard
import com.example.todo_list.model.HistoryReceive
import com.example.todo_list.model.Todos
import com.google.android.material.navigation.NavigationView
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navigationView: NavigationView
    lateinit var drawerLayout: DrawerLayout

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawerLayout = findViewById(R.id.main_layout)

        binding.btnMenu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.END)
        }

        navigationView = findViewById(R.id.navi_view)
        navigationView.setNavigationItemSelectedListener(this)

        val str = HistoryRepository().getHistory()
        str.observe(this) {
            Log.d("LIVE", it.toString())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }
}