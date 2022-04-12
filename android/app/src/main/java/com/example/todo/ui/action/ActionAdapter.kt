package com.example.todo.ui.action

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.ActionLogItemBinding
import com.example.todo.model.ActionLog


class ActionAdapter(
    actionDiffCallback: DiffUtil.ItemCallback<ActionLog>
) :
    ListAdapter<ActionLog, ActionAdapter.ViewHolder>(actionDiffCallback) {

    class ViewHolder(private val itemViewBinding: ActionLogItemBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(actionItem: ActionLog) {
            itemViewBinding.actionLog = actionItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ActionLogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}