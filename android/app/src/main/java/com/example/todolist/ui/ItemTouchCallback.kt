package com.example.todolist.ui

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max
import kotlin.math.min

class ItemTouchCallback(
    private var clamp: Float,
    private val listener: ItemTouchHelperListener,
) : ItemTouchHelper.Callback() {

    private var currentPosition: Int? = null
    private var previousPosition: Int? = null
    private var currentDX = 0f

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.START or ItemTouchHelper.END
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ): Boolean {
        listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return defaultValue * 10
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        val isClamped = (viewHolder as TaskAdapter.TaskViewHolder).getTag()
        viewHolder.setTag(!isClamped && currentDX <= -clamp)
        return 2f
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        currentDX = 0f
        previousPosition = viewHolder.adapterPosition
        getDefaultUIUtil().clearView((viewHolder as TaskAdapter.TaskViewHolder).getView())
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let {
            currentPosition = viewHolder.adapterPosition
            getDefaultUIUtil().onSelected((viewHolder as TaskAdapter.TaskViewHolder).getView())
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean,
    ) {
        val taskViewHolder = viewHolder as TaskAdapter.TaskViewHolder
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = taskViewHolder.getView()
            if (dX < 0) taskViewHolder.setVisibility(View.VISIBLE)
            else if (dX > 0) taskViewHolder.setVisibility(View.GONE)
            val isClamped = taskViewHolder.getTag()
            val x = clampViewPositionHorizontal(view, dX, isClamped, isCurrentlyActive)

            currentDX = x
            getDefaultUIUtil().onDraw(c, recyclerView, view, x, dY, actionState, isCurrentlyActive)
        }
    }

    private fun clampViewPositionHorizontal(
        view: View,
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean,
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

    fun removePreviousClamp(recyclerView: RecyclerView) {
        if (currentPosition == previousPosition) return
        previousPosition?.let {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(it) ?: return
            val taskViewHolder = viewHolder as TaskAdapter.TaskViewHolder
            taskViewHolder.getView().translationX = 0f
            taskViewHolder.setTag(false)
            previousPosition = null
        }
    }
}