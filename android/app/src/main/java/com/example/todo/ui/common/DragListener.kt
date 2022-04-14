package com.example.todo.ui.common

import android.util.Log
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
                                Log.d("ToDoid", "${viewId}")
                            }
                            rvInProgress -> target =
                                view.rootView.findViewById(R.id.rv_in_progress) as RecyclerView

                            rvDone -> target =
                                view.rootView.findViewById(R.id.rv_done) as RecyclerView
                            else -> {
                                target = view.parent as RecyclerView
                                positionTarget = view.tag as Int
                                Log.d("id", "${viewId}")
                                Log.d("test", "${target}")
                            }
                        }




                        if (viewSource != null) {
                            val source = viewSource.parent as RecyclerView // 선택한 아이템의 리사이클러뷰
                            val sourceAdapter = source.adapter as TodoAdapter  // 선택한 아이템의 리사이클러뷰의 어댑터
                            val targetAdapter = target.adapter as TodoAdapter  // 이동을 하고 놓은 장소의 어댑터
                            //getChildAdapterPosition 어댑터에서 해당 아아템의 위치 반환
                            val sourcePosition = source.getChildAdapterPosition(viewSource)  //선택한 아이템의 어댑터에서의 위치 반환
                            val sourceData = sourceAdapter.currentList[sourcePosition]  // 선택한 ToDoItem
                            val targetList = targetAdapter.currentList.toMutableList()

                            if (positionTarget >= 0) {
                                val targetData = targetAdapter.currentList[positionTarget]
                                println(targetData.title)
                                println(sourceData.title)
                                val prevItemId = targetList.find { it.next == targetData.itemId }?.itemId
                                when (positionTarget) {
                                    0 -> {
                                        when (target.id) {
                                            rvTodo -> listener.moveData(ProgressType.TO_DO, null, targetData.next, sourceData.itemId)
                                            rvInProgress -> listener.moveData(ProgressType.IN_PROGRESS, null, targetData.next, sourceData.itemId)
                                            rvDone -> listener.moveData(ProgressType.DONE, null, targetData.next, sourceData.itemId)
                                        }
                                    }
                                    targetList.lastIndex -> {
                                        when (target.id) {
                                            rvTodo -> listener.moveData(ProgressType.TO_DO, prevItemId, null, sourceData.itemId)
                                            rvInProgress -> listener.moveData(ProgressType.IN_PROGRESS, prevItemId, null, sourceData.itemId)
                                            rvDone -> listener.moveData(ProgressType.DONE, prevItemId, null, sourceData.itemId
                                            )
                                        }
                                    }
                                    else -> {
                                        when (target.id) {
                                            rvTodo -> listener.moveData(ProgressType.TO_DO, prevItemId, targetData.next, sourceData.itemId
                                            )
                                            rvInProgress -> listener.moveData(ProgressType.IN_PROGRESS, prevItemId, targetData.next, sourceData.itemId
                                            )
                                            rvDone -> listener.moveData(ProgressType.DONE, prevItemId, targetData.next, sourceData.itemId
                                            )
                                        }
                                    }
                                }
                            } else {
                                val targetData = targetList[targetList.lastIndex]
                                val prevDataId = targetList.find { it.next == targetData.itemId }?.itemId
                                println(targetData.title)
                                println(sourceData.title)
                                when (target.id) {
                                    rvTodo -> listener.moveData(ProgressType.TO_DO, prevDataId, null, sourceData.itemId
                                    )
                                    rvInProgress -> listener.moveData(ProgressType.IN_PROGRESS, prevDataId, null, sourceData.itemId
                                    )
                                    rvDone -> listener.moveData(ProgressType.DONE, prevDataId, null, sourceData.itemId
                                    )
                                }
                            }


                        }
                        //item/  이동할곳 previd  이동할곳 nextID  destination   itemID

                    }
                }
            }
        }
        return true
    }


}
