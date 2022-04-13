package com.example.todo_list.tasks

import android.graphics.Canvas
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_list.R
import kotlin.math.min

class ItemTouchHelperCallback(val listener: ItemTouchHelperListener) : ItemTouchHelper.Callback() {

    private var clamp = 0f
    private var currentDx = 0f

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        Log.d("TAGcode", "${ItemTouchHelper.LEFT}")
        return makeMovementFlags(0, ItemTouchHelper.LEFT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Log.d("TAGonSwiped1", "$direction")
        Log.d("TAGonSwiped2", "${viewHolder.adapterPosition}") //<< 몇번째 목록을 조작했는지
        // direction = 방향 >> 왼쪽으로 스와이프했을때 작동
        listener.onItemSwipe(viewHolder.adapterPosition)

    }

    // 터치,스와이프등 뷰에 변화가 생길겨여우
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
                getView(viewHolder).animate().translationX(-clamp).setDuration(100L).start()
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

    fun setClamp(clamp: Float) { this.clamp = clamp }
    private fun getView(viewHolder: RecyclerView.ViewHolder) : View = viewHolder.itemView.findViewById(R.id.todo_item)
    private fun getTag(viewHolder: RecyclerView.ViewHolder) : Boolean =  viewHolder.itemView.tag as? Boolean ?: false

    private fun clampViewPositionHorizontal(
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean
    ) : Float {
        // RIGHT 방향으로 swipe 막기
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
        return min(newX, max)
    }
}