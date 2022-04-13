package com.example.todo.ui.common

import android.graphics.Canvas
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.ui.toDo.TodoAdapter
import java.lang.Float.max
import java.lang.Float.min

class ToDoTouchHelper: ItemTouchHelper.Callback(){
    private var currentDx = 0f
    private var clamp = 0f
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.START
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        val isClamped = getTag(viewHolder)
        setTag(viewHolder, !isClamped && currentDx <= -clamp)
        return 0.5f
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        if (actionState == ACTION_STATE_SWIPE) {
            val view = getView(viewHolder)
            val isClamped = getTag(viewHolder)
            val x = clampViewPositionHorizontal(view, dX, isClamped, isCurrentlyActive)
            if (dX < 0) getDeleteView(viewHolder).visibility = View.VISIBLE
            else if (dX > 0) getDeleteView(viewHolder).visibility = View.GONE
            currentDx = x

            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                view,
                x,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clampViewPositionHorizontal(
        view: View,
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean
    ): Float {
        val min: Float = -view.width.toFloat() / 2
        val max: Float = 0f

        val x = if (isClamped) {
            if (isCurrentlyActive) dX - clamp else -clamp
        } else {
            dX
        }

        return min(max(min, x), max)
    }


    private fun getTag(viewHolder: RecyclerView.ViewHolder): Boolean {
        return viewHolder.itemView.findViewById<ConstraintLayout>(R.id.toDoLayout).tag as? Boolean
            ?: false
    }

    private fun setTag(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean) {
        viewHolder.itemView.findViewById<ConstraintLayout>(R.id.toDoLayout).tag = isClamped
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as TodoAdapter.ViewHolder).itemView.findViewById(R.id.toDoLayout)
    }

    private fun getDeleteView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as TodoAdapter.ViewHolder).itemView.findViewById(R.id.delete_view)
    }
}
