package com.example.todo.ui.common

import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.model.ProgressType
import com.example.todo.ui.toDo.TodoAdapter


class DragListener(private val listener: ToDoMoveListener) : View.OnDragListener {
    private var isDrop = false
    override fun onDrag(view: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                isDrop = true
                var positionTarget = -1
                val viewSource = event.localState as View?

                val viewId = view.id
                val rvTodo = R.id.rv_todo
                val todoItem = R.id.todo_item
                val rvInProgress = R.id.rv_in_progress
                val rvDone = R.id.rv_done

                when (viewId) {
                    rvTodo, rvDone, rvInProgress, todoItem -> {
                        val target: RecyclerView
                        when (viewId) {
                            rvTodo -> {
                                target = view.rootView.findViewById(R.id.rv_todo) as RecyclerView
                            }
                            rvInProgress -> target =
                                view.rootView.findViewById(R.id.rv_in_progress) as RecyclerView

                            rvDone -> target =
                                view.rootView.findViewById(R.id.rv_done) as RecyclerView
                            else -> {
                                target = view.parent as RecyclerView
                                positionTarget = view.tag as Int
                            }
                        }

                        if (viewSource != null) {
                            val source = viewSource.parent as RecyclerView // 선택한 아이템의 리사이클러뷰
                            val sourceAdapter =
                                source.adapter as TodoAdapter  // 선택한 아이템의 리사이클러뷰의 어댑터
                            val targetAdapter = target.adapter as TodoAdapter  // 이동을 하고 놓은 장소의 어댑터
                            //getChildAdapterPosition 어댑터에서 해당 아아템의 위치 반환
                            val sourcePosition =
                                source.getChildAdapterPosition(viewSource)  //선택한 아이템의 어댑터에서의 위치 반환

                            val sourceList = sourceAdapter.currentList.toMutableList()

                            val sourceData = sourceList[sourcePosition]  // 선택한 ToDoItem
                            val targetList = targetAdapter.currentList.toMutableList()

                            if (positionTarget >= 0) {
                                println(targetList)
                                println(positionTarget)
                                val targetData = targetAdapter.currentList[positionTarget]
                                val prevItemId =
                                    targetList.find { it.itemId == targetData.itemId }?.itemId
                                println("test ${positionTarget}")
                                when (positionTarget) {
                                    0 -> {
                                        println("there!!!!!!!!!!!!!!!!!!!")
                                        println(prevItemId)
                                        println(targetData.next)
                                        println(sourceData.itemId)
                                        when (target.id) {
                                            rvTodo -> listener.moveData(
                                                ProgressType.TO_DO,
                                                prevItemId,
                                                null,
                                                sourceData.itemId
                                            )
                                            rvInProgress -> listener.moveData(
                                                ProgressType.IN_PROGRESS,
                                                prevItemId,
                                                null,
                                                sourceData.itemId
                                            )
                                            rvDone -> listener.moveData(
                                                ProgressType.DONE,
                                                prevItemId,
                                                null,
                                                sourceData.itemId
                                            )
                                        }
                                    }
                                    else -> {
                                        println("here!!!!!!!!!!!!!!!!!")
                                        println(prevItemId)
                                        println(targetData.itemId)
                                        println(targetData.next)
                                        when (target.id) {

                                            rvTodo -> listener.moveData(
                                                ProgressType.TO_DO,
                                                targetData.itemId,
                                                targetData.next,
                                                sourceData.itemId
                                            )
                                            rvInProgress -> listener.moveData(
                                                ProgressType.IN_PROGRESS,
                                                targetData.itemId,
                                                targetData.next,
                                                sourceData.itemId
                                            )
                                            rvDone -> listener.moveData(
                                                ProgressType.DONE,
                                                targetData.itemId,
                                                targetData.next,
                                                sourceData.itemId
                                            )
                                        }
                                    }
                                }
                            } else {
                                println(targetList)
                                println(positionTarget)


                                val targetData = targetList[targetList.lastIndex]
                                println(targetData.itemId)
                                val prevDataId =
                                    targetList.find { it.next == targetData.itemId }?.itemId ?: 0
                                if (targetList.isEmpty()) {
                                    when (target.id) {
                                        rvTodo -> listener.moveData(
                                            ProgressType.TO_DO,
                                            null,
                                            null,
                                            sourceData.itemId
                                        )
                                        rvInProgress -> listener.moveData(
                                            ProgressType.IN_PROGRESS,
                                            null, null,
                                            sourceData.itemId
                                        )
                                        rvDone -> listener.moveData(
                                            ProgressType.DONE,
                                            null,
                                            null,
                                            sourceData.itemId
                                        )
                                    }
                                }
                                else{
                                    when (target.id) {
                                        rvTodo -> listener.moveData(
                                            ProgressType.TO_DO,
                                            null,
                                            targetData.itemId,
                                            sourceData.itemId
                                        )
                                        rvInProgress -> listener.moveData(
                                            ProgressType.IN_PROGRESS,
                                            null, targetData.itemId,
                                            sourceData.itemId
                                        )
                                        rvDone -> listener.moveData(
                                            ProgressType.DONE,
                                            null,
                                            targetData.itemId,
                                            sourceData.itemId
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true
    }


}
