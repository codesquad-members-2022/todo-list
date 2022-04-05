package com.example.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.todolist.R
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private val mDrawerLayout : DrawerLayout by lazy {
        findViewById(R.id.dlo_appbar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val appbar: MaterialToolbar = findViewById(R.id.top_appbar)
        appbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.log -> {
                    Log.d("AppTest", "${it.itemId}")
                    if(!mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                        mDrawerLayout.openDrawer(GravityCompat.END)
                    }
                }

                else -> {
                    Log.d("AppTest", "${it.itemId}")
                    if(mDrawerLayout.isDrawerOpen(GravityCompat.END)) {
                        mDrawerLayout.closeDrawer(GravityCompat.END)
                    }
                }
            }
            true
        }
    }

    override fun onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.END)){
            mDrawerLayout.closeDrawers()
        }else{
            super.onBackPressed()
        }
    }
}