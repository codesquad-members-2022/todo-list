package com.codesquad.aos.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.toolbarMenu?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.appbar_main_menu -> {
                    Log.d("AppTest", "appbar-menu")
                    binding.drawerLayout?.openDrawer(Gravity.RIGHT)

                    // binding.drawerLaytout?.open()
                    // 위 방식으로 하면 안드로이드 자체에서 gravity가 left 인 드로어를 필요로 하는 것으로 이해
                    // 그래서 앱이 죽는현상 발생

                    true
                }
                else -> false
            }
        }

        binding.drawerLayout?.addDrawerListener(object: DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                Log.d("AppTest", "onDrawerSlide called")

            }

            override fun onDrawerOpened(drawerView: View) {
                Log.d("AppTest", "onDrawerOpened called")

                binding.tvInDrawer?.text = "change success"

            }

            override fun onDrawerClosed(drawerView: View) {

            }

            override fun onDrawerStateChanged(newState: Int) {

            }

        })

    }
}