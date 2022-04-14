package com.example.todo_list.tasks

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R

class ItemTouchHelperCallback(private val TaskAdapter : TaskAdapter) : ItemTouchHelper.Callback() {

    private var clamp = 0f
    private var currentDx = 0f

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT)
    }

    // 왜 안옮겨지지? ..>> 길게클릭해야 이벤트발생
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val fromPos = viewHolder.adapterPosition
        val toPos = target.adapterPosition
        TaskAdapter.swapData(fromPos, toPos)
        return true
    }

    // Swiped 자체가 실행하면 데이터 삭제기능을 겸함, 아래 onChildDraw 에서 swipe 제한길이를 정해둬서 실행되지않음
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
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = getView(viewHolder)
            val isClamped = getTag(viewHolder)
            val newX = clampViewPositionHorizontal(
                dX,
                isClamped,
                isCurrentlyActive
            )  // newX 만큼 이동(고정 시 이동 위치/고정 해제 시 이동 위치 결정)

            // 고정시킬 시 애니메이션 추가
            if (newX == -clamp) {
                view.animate().translationX(-clamp).setDuration(50).start()
                return
            }

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

    private fun getView(viewHolder: RecyclerView.ViewHolder): View =
        viewHolder.itemView.findViewById(R.id.todo_item)

    private fun getTag(viewHolder: RecyclerView.ViewHolder): Boolean =
        viewHolder.itemView.tag as? Boolean ?: false

    private fun clampViewPositionHorizontal(
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean
    ): Float {

        // 고정할 수 있으면
        val newX = if (isClamped) {
            // 현재 swipe 중이면 swipe되는 영역 제한
            if (isCurrentlyActive)
            // 오른쪽 swipe일 때
                if (dX < 0) {
                    dX / 3 - clamp
                }
                // 왼쪽 swipe일 때
                else dX - clamp
            // swipe 중이 아니면 고정시키기
            else -clamp
        }
        // 고정할 수 없으면 newX는 스와이프한 만큼
        else dX / 2

        return newX
    }
}