package com.example.todolist.ui.common

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.data.Card

object DiffUtil : DiffUtil.ItemCallback<Card>() {

    override fun areItemsTheSame(oldItem: Card, newItem: Card) : Boolean  {
        Log.d("AppTest", "${oldItem == newItem}")
        return oldItem.cardId == newItem.cardId
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        Log.d("AppTest", "${oldItem == newItem}")
        return oldItem == newItem
    }

}