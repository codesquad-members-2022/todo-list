package com.codesquad.aos.todolist.ui

import android.graphics.Canvas
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.ui.adapter.TodoCardListAdapter

class TodoTouchHelper(private val recyclerViewAdapter: TodoCardListAdapter): ItemTouchHelper.Callback() {

    private var currentPosition: Int? = null
    private var previousPosition: Int? = null
    private var currentDx = 0f
    private var clamp = 0f
    //commit
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val from: Int = viewHolder.absoluteAdapterPosition
        val to: Int = target.absoluteAdapterPosition
        recyclerViewAdapter.moveItem(from, to)
        return true
    }


    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        Log.d("TodoTouchHelper", "onSelectedChanged")

        viewHolder?.let {
            currentPosition = viewHolder.absoluteAdapterPosition
            if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                (viewHolder as TodoCardListAdapter.CardViewHolder).itemView.alpha = 0.5f
            }

            getDefaultUIUtil().onSelected(getView(it))
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        Log.d("TodoTouchHelper", "clearView")
        currentDx = 0f
        (viewHolder as TodoCardListAdapter.CardViewHolder).itemView.alpha = 1f
        getDefaultUIUtil().clearView(getView(viewHolder))
        previousPosition = viewHolder.absoluteAdapterPosition
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        val isClamped = getTag(viewHolder)
        setTag(viewHolder, !isClamped && currentDx <= -clamp)
        return 2f
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

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
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            Log.d("onChildDraw", "ACTION_STATE_SWIPE")
            val view = getView(viewHolder)
            val isClamped = getTag(viewHolder)
            val newX = clampViewPositionHorizontal(view, dX, isClamped, isCurrentlyActive)

            if (newX == -clamp) {
                getView(viewHolder).animate().translationX(-clamp).setDuration(100L).start()
                return
            }

            currentDx = newX
            ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(
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

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clampViewPositionHorizontal(
        view: View,
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean
    ) : Float {
        // View의 가로 길이의 절반까지만 swipe 되도록
        val max = 0f

        // 고정할 수 있으면
        val newX = if (isClamped) {
            // 현재 swipe 중이면 swipe되는 영역 제한
            if (isCurrentlyActive)
            // 오른쪽 swipe일 때
                if (dX < 0) dX/3 - clamp
                // 왼쪽 swipe일 때
                else dX - clamp
            // swipe 중이 아니면 고정시키기
            else -clamp
        }
        // 고정할 수 없으면 newX는 스와이프한 만큼
        else dX / 2

        // newX가 0보다 작은지 확인
        return java.lang.Float.min(newX, max)
    }

    private fun setTag(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean) {
        viewHolder.itemView.tag = isClamped
    }

    private fun getTag(viewHolder: RecyclerView.ViewHolder): Boolean {
        return viewHolder.itemView.tag as? Boolean ?: false
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return viewHolder.itemView.findViewById(R.id.cvSwipeView)
    }

    fun setClamp(clamp: Float) {
        this.clamp = clamp
    }

    fun removePreviousClamp(recyclerView: RecyclerView) {
//        Log.d("removePreviousClamp", "removeViewOverStart")

        if (currentPosition == previousPosition) {
//            Log.d("removePreviousClamp", "removeViewOver")
            return
        }

        previousPosition?.let {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(it) ?: return
            getView(viewHolder).translationX = 0f
            setTag(viewHolder, false)
            previousPosition = null
        }


//            previousPosition?.let {
//                Log.d("removePreviousClamp", "removeView")
//
//                val viewHolder = recyclerView.findViewHolderForAdapterPosition(it) ?: return
//                getView(viewHolder).animate().x(0f).setDuration(100L).start()
//                setTag(viewHolder, false)
//                previousPosition = null
//            }
    }
}