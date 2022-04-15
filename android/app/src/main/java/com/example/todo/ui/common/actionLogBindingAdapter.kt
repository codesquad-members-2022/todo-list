
package com.example.todo.ui.common

import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.example.todo.common.getTimeDiff
import com.example.todo.model.ActionLog
import com.example.todo.model.ActionType

@BindingAdapter("actionLogTime")
fun getAfterTime(textView: TextView, time: String) {
    textView.text = getTimeDiff(time)
}


//수정  추가. 삭제, 이동

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("actionLogMessage")
fun getMessage(textView: TextView, actionLog:ActionLog){
    var message=""
    val title= "<strong>${actionLog.title}</strong>"
    val prevProgressType= "<strong>${actionLog.prevProgressType}</strong>"
    val nowProgressType= "<strong>${actionLog.nowProgressType}</strong>"
    val action= "<strong>${actionLog.actionType}</strong>"
    message = when(actionLog.actionType){
        ActionType.MOVE->{
            "${title}가 ${prevProgressType} 에서 ${nowProgressType} 으로 ${action} 되었습니다".htmlToString().toString()
        }
        else->{
            ("${title}가  ${nowProgressType} 에서 ${action} 되었습니다").htmlToString().toString()
        }

    }

    textView.text= message

}