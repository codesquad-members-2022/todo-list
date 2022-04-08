package com.example.todo.common

import androidx.recyclerview.widget.DiffUtil
import com.example.todo.model.ActionLog

class ActionDiffCallback : DiffUtil.ItemCallback<ActionLog>() {
    override fun areItemsTheSame(oldItem: ActionLog, newItem: ActionLog): Boolean {
        // 추후 id 변수를 추가하고 이 부분 수정 필요
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ActionLog, newItem: ActionLog): Boolean {
        return oldItem == newItem
    }
}