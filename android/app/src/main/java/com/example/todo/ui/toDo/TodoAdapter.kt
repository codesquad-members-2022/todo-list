
package com.example.todo.ui.toDo


import android.content.ClipData
import android.content.Context
import android.os.Build
import android.view.*


import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.TodoItemBinding
import com.example.todo.model.TodoItem
import com.example.todo.ui.common.DragListener
import com.example.todo.ui.common.ToDoMoveListener

class TodoAdapter(
    private val context: Context,
    private val listener: UpdateDialogListener,
    private val toDoMoveListener: ToDoMoveListener,
    private val viewModel: ToDoViewModel,
    todoDiffCallback: DiffUtil.ItemCallback<TodoItem>
) :
    ListAdapter<TodoItem, TodoAdapter.ViewHolder>(todoDiffCallback),View.OnTouchListener  {


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
            itemViewBinding.deleteView.setOnClickListener {
                viewModel.deleteItem(cardItem)
            }

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {

            }
            MotionEvent.ACTION_MOVE->{
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(v)
                v?.startDragAndDrop(data, shadowBuilder, v, 0)
                return true
            }
        }
        return false
    }

    val dragInstance: DragListener?
        get() = DragListener(toDoMoveListener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.tag= position
        holder.itemView.setOnTouchListener(this)
        holder.itemView.setOnDragListener(DragListener(toDoMoveListener))
    }

}
