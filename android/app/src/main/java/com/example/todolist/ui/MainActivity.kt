package com.example.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import androidx.databinding.DataBindingUtil
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btnDrawer.setOnClickListener {
            binding.dlDrawer.openDrawer(Gravity.RIGHT)
        }
    }
}
