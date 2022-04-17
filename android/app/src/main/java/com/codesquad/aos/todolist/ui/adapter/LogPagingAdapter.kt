package com.codesquad.aos.todolist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.data.model.LogX
import com.codesquad.aos.todolist.databinding.ItemLogBinding

class LogPagingAdapter: PagingDataAdapter<LogX, LogPagingAdapter.LogViewHolder>(diffUtil) {

    class LogViewHolder(val binding: ItemLogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(logX: LogX?) {
            logX?.let {
                binding.tvLogUser?.text = "@hanjo&fiona"
                val content: String = when(it.logEventType){
                    "move" -> {
                        "${it.title} 를 ${it.prevSection} 에서 ${it.section} 로 이동 하였습니다."
                    }
                    "create" -> {
                        "${it.section} 에 ${it.title} 을/를 등록 하였습니다."
                    }
                    "delete" -> {
                        "${it.section} 에서 ${it.title} 을/를 삭제 하였습니다."
                    }
                    "update" -> {
                        "${it.section} 의 ${it.title} 을/를 수정 하였습니다"
                    }
                    else -> {
                        "empty"
                    }
                }
                binding.tvLogContent?.text = content
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemLogBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_log, parent, false)
        return LogViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<LogX>() {
            override fun areItemsTheSame(
                oldItem: LogX,
                newItem: LogX
            ): Boolean {  // id, 주민번호 등 특징적인 것으로 비교
                return oldItem.logTime == newItem.logTime
            }

            override fun areContentsTheSame(
                oldItem: LogX,
                newItem: LogX
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}