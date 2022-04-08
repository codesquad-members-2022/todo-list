package com.example.todo.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.todo.common.getTimeDiff
import java.text.DecimalFormat

@BindingAdapter("actionLogTime")
fun getAfterTime(textView: TextView, time: String) {
    textView.text = getTimeDiff(time)
}
