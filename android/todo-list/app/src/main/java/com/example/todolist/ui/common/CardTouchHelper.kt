package com.example.todolist.ui.common

import android.graphics.Canvas
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.ui.adapter.CardAdapter

class CardTouchHelper :
    ItemTouchHelper.Callback() {

    private var currentDx = 0f
    private var deleteTextViewSize = 0f
    private val clampSpace = 2.5f

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        deleteTextViewSize = getDeleteTextViewWidth(viewHolder as CardAdapter.CardViewHolder)
        return makeMovementFlags(UP or DOWN, START)
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        setTag((viewHolder as CardAdapter.CardViewHolder), currentDx <= -deleteTextViewSize)
        return 2f
    }

    private fun getDeleteTextViewWidth(viewHolder: CardAdapter.CardViewHolder): Float {
        return viewHolder.getDeleteTextViewWidth()
    }

    private fun getDeleteTextView(viewHolder: CardAdapter.CardViewHolder): TextView? {
        return viewHolder.getDeleteTextView()
    }

    private fun getSwipeView(viewHolder: CardAdapter.CardViewHolder): ConstraintLayout? {
        return viewHolder.getSwipeView()
    }

    private fun getTag(viewHolder: CardAdapter.CardViewHolder): Boolean {
        return viewHolder.getTag()
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
            viewHolder as CardAdapter.CardViewHolder
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
        val newX = checkClamped(isClamped, isCurrentlyActive, dX)
        return if (newX < 0) {
            textView.visibility = View.VISIBLE
            newX
        } else {
            textView.visibility = View.GONE
            0f
        }
    }

    private fun checkClamped(isClamped: Boolean, isCurrentlyActive: Boolean, dX: Float) =
        when (isClamped) {
            true -> checkCurrentlyActive(isCurrentlyActive, dX)
            false -> dX / clampSpace

        }

    private fun checkCurrentlyActive(isCurrentlyActive: Boolean, dX: Float) =
        when (isCurrentlyActive) {
            true -> checkMoved(dX)
            false -> -deleteTextViewSize
        }

    private fun checkMoved(dX: Float) = when (dX < 0) {
        true -> dX / 3 - deleteTextViewSize
        false -> dX - deleteTextViewSize
    }

    private fun setTag(viewHolder: CardAdapter.CardViewHolder, isClamped: Boolean) {
        viewHolder.setTag(isClamped)
    }

}
