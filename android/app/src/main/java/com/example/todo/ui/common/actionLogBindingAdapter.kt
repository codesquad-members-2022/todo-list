
package com.example.todo.ui.common

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.example.todo.common.getTimeDiff
import com.example.todo.model.ActionLog

@BindingAdapter("actionLogTime")
fun getAfterTime(textView: TextView, time: String) {
    textView.text = getTimeDiff(time)
}

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("actionLogMessage")
fun getMessage(textView: TextView, actionLog:ActionLog){
    val title= "<strong>${actionLog.title}</strong>"
    val prevProgressType= "<strong>${actionLog.prevProgressType}</strong>"
    val nowProgressType= "<strong>${actionLog.nowProgressType}</strong>"
    val action= "<strong>${actionLog.actionType}</strong>"
    textView.text=  ( "${title}가 ${prevProgressType} 에서 ${nowProgressType} 으로 ${action} 했습니다").htmlToString()

}