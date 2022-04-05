package com.example.todo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.model.TodoItem

class TodoAdapter(
    todoDiffCallback: DiffUtil.ItemCallback<TodoItem>
) :
    ListAdapter<TodoItem, TodoAdapter.ViewHolder>(todoDiffCallback) {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_item_title)
        private val content: TextView = itemView.findViewById(R.id.tv_item_content)
        fun bind(cardItem: TodoItem) {
            title.text = cardItem.title
            content.text = cardItem.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}