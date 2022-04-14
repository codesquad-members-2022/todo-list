
package com.example.todo.ui.toDo


import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.TodoItemBinding
import com.example.todo.model.TodoItem

class TodoAdapter(
    private val context: Context,
    private val listener: UpdateDialogListener,
    private val viewModel: ToDoViewModel,
    todoDiffCallback: DiffUtil.ItemCallback<TodoItem>
) :
    ListAdapter<TodoItem, TodoAdapter.ViewHolder>(todoDiffCallback) {


    interface UpdateDialogListener {
        fun updateDialog(item: TodoItem)
    }

    inner class ViewHolder(private val itemViewBinding: TodoItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root),
        PopupMenu.OnMenuItemClickListener {
        private lateinit var cardItem: TodoItem
        fun bind(cardItem: TodoItem) {
            this.cardItem = cardItem
            itemViewBinding.toDoItem = cardItem
            itemView.setOnLongClickListener {
                displayPopupMenu(it)
                true
            }

        private fun displayPopupMenu(view: View) {
            val popupMenu = PopupMenu(context, view)
            popupMenu.menuInflater.inflate(R.menu.menu_popup, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(this)
            popupMenu.show()
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.popup_move_to_done ->  viewModel.moveToDone(cardItem)
                R.id.popup_update -> listener.updateDialog(cardItem)
                R.id.popup_delete -> viewModel.deleteItem(cardItem)
            }
            return true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
