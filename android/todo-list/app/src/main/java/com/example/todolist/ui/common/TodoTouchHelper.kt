package com.example.todolist.ui.common

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class TodoTouchHelper :
    ItemTouchHelper.Callback() {
    private var currentDx = 0f
    private var deleteTextViewSize = 0f
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        deleteTextViewSize = getDeleteTextViewWidth(viewHolder)
        return makeMovementFlags(UP or DOWN, LEFT or RIGHT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPos = viewHolder.adapterPosition
        val toPos = viewHolder.adapterPosition
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
            val view = getSwipeView(viewHolder)
            val isClamped = getTag(viewHolder)
            val deleteTextView = getDeleteTextView(viewHolder)
            val newX = swipe(isClamped, isCurrentlyActive, dX, deleteTextView)
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

    @SuppressLint("NewApi")
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
            dX / 3.5f
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
        setTag(viewHolder, currentDx <= -deleteTextViewSize)
        return 2f
    }

    private fun getDeleteTextViewWidth(viewHolder: RecyclerView.ViewHolder) =
        viewHolder.itemView.findViewById<View>(R.id.tv_delete_card).width.toFloat()

    private fun getDeleteTextView(viewHolder: RecyclerView.ViewHolder) =
        viewHolder.itemView.findViewById<View>(R.id.tv_delete_card)

    private fun getSwipeView(viewHolder: RecyclerView.ViewHolder): View =
        viewHolder.itemView.findViewById(R.id.cio_screen)

    private fun setTag(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean) {
        viewHolder.itemView.tag = isClamped
    }

    private fun getTag(viewHolder: RecyclerView.ViewHolder): Boolean =
        viewHolder.itemView.tag as? Boolean ?: false
}
