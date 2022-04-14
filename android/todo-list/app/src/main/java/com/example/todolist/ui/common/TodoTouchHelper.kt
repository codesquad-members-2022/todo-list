package com.example.todolist.ui.common

import android.graphics.Canvas
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.ui.adapter.TodoAdapter

class TodoTouchHelper :
    ItemTouchHelper.Callback() {

    private var currentDx = 0f
    private var deleteTextViewSize = 0f

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        deleteTextViewSize = getDeleteTextViewWidth(viewHolder as TodoAdapter.TodoViewHolder)
        return makeMovementFlags(UP or DOWN, START)
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
            viewHolder as TodoAdapter.TodoViewHolder
            val view = getSwipeView(viewHolder)
            val isClamped = getTag(viewHolder)
            val deleteTextView = getDeleteTextView(viewHolder)

            val newX = deleteTextView?.let { swipe(isClamped, isCurrentlyActive, dX, it) } ?: 0f

            currentDx = newX
            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                view,
                newX,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }

    private fun swipe(
        isClamped: Boolean,
        isCurrentlyActive: Boolean,
        dX: Float,
        textView: View
    ): Float {
        val newX = if (isClamped) {
            if (isCurrentlyActive) {
                if (dX < 0) {
                    dX / 3 - deleteTextViewSize
                } else {
                    dX - deleteTextViewSize
                }
            } else {
                -deleteTextViewSize
            }
        } else {
            dX / 2f
        }
        return if (newX < 0) {
            textView.visibility = View.VISIBLE
            newX
        } else {
            textView.visibility = View.GONE
            0f
        }
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        setTag((viewHolder as TodoAdapter.TodoViewHolder), currentDx <= -deleteTextViewSize)
        return 2f
    }

    private fun getDeleteTextViewWidth(viewHolder: TodoAdapter.TodoViewHolder): Float {
        return viewHolder.getDeleteTextViewWidth()
    }


    private fun getDeleteTextView(viewHolder: TodoAdapter.TodoViewHolder): TextView? {
        return viewHolder.getDeleteTextView()
    }


    private fun getSwipeView(viewHolder: TodoAdapter.TodoViewHolder): ConstraintLayout? {
        return viewHolder.getSwipeView()
    }


    private fun setTag(viewHolder: TodoAdapter.TodoViewHolder, isClamped: Boolean) {
        viewHolder.setTag(isClamped)
    }

    private fun getTag(viewHolder: TodoAdapter.TodoViewHolder): Boolean {
        return viewHolder.getTag()
    }

}
