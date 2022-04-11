package com.example.todolist.ui.common

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class TodoTouchHelper :
    ItemTouchHelper.Callback() {
    private var currentPosition: Int? = null    // 현재 선택된 recycler view의 position
    private var previousPosition: Int? = null   // 이전에 선택했던 recycler view의 position
    private var currentDx = 0f                  // 현재 x 값
    private var isSwiped = false
    private var swipedViewPosition = -1
    private var clamp = 0f
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
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
            val view = getView(viewHolder)
            val textView = getTv(viewHolder)
            val textSize = getWidth(viewHolder)
            val newX = if ((dX / 2) > 0) {
                textView.visibility = View.GONE
                0f
            } else {
                textView.visibility = View.VISIBLE
                dX / 2
            }
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

    private fun setSwipedView(viewHolder: RecyclerView.ViewHolder) {
        swipedViewPosition = viewHolder.adapterPosition
    }

    private fun getWidth(viewHolder: RecyclerView.ViewHolder) =
        viewHolder.itemView.findViewById<View>(R.id.tv_delete_card).width.toFloat()

    fun getSwipedView() = swipedViewPosition

    private fun getTv(viewHolder: RecyclerView.ViewHolder) =
        viewHolder.itemView.findViewById<View>(R.id.tv_delete_card)

    private fun getView(viewHolder: RecyclerView.ViewHolder): View =
        viewHolder.itemView.findViewById(R.id.cio_screen)
}

// 나중에 논의할 것
/*@SuppressLint("NewApi")
private fun clampViewPositionHorizontal(
    dX: Float,
    isClamped: Boolean,
    isCurrentlyActive: Boolean
): Float {
    // RIGHT 방향으로 swipe 막기
    val max = 0f
    // 고정할 수 있으면
    *//*val newX = if (isClamped) {
        Log.d("안되는거?", "$isClamped")
        // 현재 swipe 중이면 swipe되는 영역 제한
        if (isCurrentlyActive)
        // 오른쪽 swipe일 때
            if (dX < 0) dX / 3 - test
            // 왼쪽 swipe일 때
            else dX - test
        // swipe 중이 아니면 고정시키기
        else {
            Log.d("되는거?", "$isClamped")
            -test
        }
    }
    // 고정할 수 없으면 newX는 스와이프한 만큼
    else {
        Log.d("ㄹㅇ되는거?", "$isClamped")
        dX / 2
    }*//**//*
    return min(max, dX / 2)
}
private fun setTag(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean) {
    viewHolder.itemView.tag = isClamped
}
private fun getTag(viewHolder: RecyclerView.ViewHolder): Boolean =
    viewHolder.itemView.tag as? Boolean ?: false
fun setClamp(clamp: Float) {
    this.clamp = clamp
}
private fun getWidth(viewHolder: RecyclerView.ViewHolder) = viewHolder.itemView.width
private fun setWidth(viewHolder: RecyclerView.ViewHolder) {
    this.test = viewHolder.itemView.findViewById<TextView>(R.id.tv_delete_card).width.toFloat()
    this.clamp = viewHolder.itemView.width.toFloat()
}*/
