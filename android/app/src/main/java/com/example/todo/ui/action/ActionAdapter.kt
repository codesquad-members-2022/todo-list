package com.example.todo.ui.action

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.common.getTimeDiff
import com.example.todo.model.ActionLog
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDate
import org.joda.time.Seconds
import org.joda.time.format.DateTimeFormat
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class ActionAdapter(
    actionDiffCallback: DiffUtil.ItemCallback<ActionLog>
) :
    ListAdapter<ActionLog, ActionAdapter.ViewHolder>(actionDiffCallback) {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val content: TextView = itemView.findViewById(R.id.tv_action_content)
        private val time: TextView = itemView.findViewById(R.id.tv_action_time)

        fun bind(actionItem: ActionLog) {
            content.text = actionItem.toString()
            time.text = getTimeDiff(actionItem.time)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.action_log_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}