package com.codesquad.aos.todolist.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.data.model.Log
import com.codesquad.aos.todolist.databinding.ItemLogBinding

class LogCardListAdapter : ListAdapter<Log, LogCardListAdapter.LogCardViewHolder>(diffUtil) {

    class LogCardViewHolder(private val binding: ItemLogBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind (log: Log) {
                binding.tvLogUser?.text = log.id
                binding.tvLogContent?.text = log.log
                binding.tvLogTime?.text = log.time
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogCardViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemLogBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_log, parent, false)
        return LogCardViewHolder(binding)

    }

    override fun onBindViewHolder(holder: LogCardViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Log>() {
            override fun areItemsTheSame(
                oldItem: Log,
                newItem: Log
            ): Boolean {  // id, 주민번호 등 특징적인 것으로 비교
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Log,
                newItem: Log
            ): Boolean {
                return oldItem == newItem  // 내용들이 같은지 체크,  java의 equals !!
            }
            // areItemsTheSame 이 참이면 areContentsTheSame 가 호출된다
        }
    }

}