package com.example.todo.ui.action

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.model.ActionLog
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class ActionAdapter(
    actionDiffCallback: DiffUtil.ItemCallback<ActionLog>
) :
    ListAdapter<ActionLog, ActionAdapter.ViewHolder>(actionDiffCallback) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val content: TextView = itemView.findViewById(R.id.tv_action_content)
        private val time: TextView = itemView.findViewById(R.id.tv_action_time)

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(actionItem: ActionLog) {
            content.text = actionItem.toString()
            time.text = getTimeDiff(actionItem.time)
        }



        @RequiresApi(Build.VERSION_CODES.O)
        fun getTimeDiff(actionDateTime: LocalDateTime):String {
            val nowDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
            val duration=  Duration.between(actionDateTime, nowDateTime)
            return when {
                duration.toDays() > 0 -> {
                    "${duration.toDays()}일전"
                }
                duration.toHours()>0 -> {
                    "${duration.toHours()}시간전"
                }
                else -> {
                    "${duration.toMinutes()}분전"
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.action_log_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}