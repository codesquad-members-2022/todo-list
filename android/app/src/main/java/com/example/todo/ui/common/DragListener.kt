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
                val todoItem= R.id.todo_item
                val rvInProgress = R.id.rv_in_progress
                val rvDone = R.id.rv_done
                Log.d("id", "${viewId}")
                when (viewId) {
                    rvTodo, rvDone, rvInProgress, todoItem -> {
                        val target: RecyclerView
                        when (viewId) {
                            rvTodo -> target =
                                view.rootView.findViewById(R.id.rv_todo) as RecyclerView
                            rvInProgress -> target =
                                view.rootView.findViewById(R.id.rv_in_progress) as RecyclerView
                            rvDone -> target =
                                view.rootView.findViewById(R.id.rv_done) as RecyclerView
                            else -> {
                                target = view.parent as RecyclerView
                                positionTarget = view.tag as Int
                                Log.d("test", "${target}")
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
                            val sourceData =
                                sourceAdapter.currentList[sourcePosition]  // 선택한 ToDoItem
                            val sourceList =
                                sourceAdapter.currentList.toMutableList()  // 선택한 아이템이 포함된 리스트



                            Log.d("test", "different recycler")
                            //이동하려는 아이템 삭제
                            when (source.id) {
                                rvTodo -> listener.deleteData(ProgressType.TO_DO, sourceData)
                                rvInProgress -> listener.deleteData(
                                    ProgressType.IN_PROGRESS,
                                    sourceData
                                )
                                rvDone -> listener.deleteData(ProgressType.DONE, sourceData)
                            }
                            val targetList = targetAdapter.currentList.toMutableList()

                            if (positionTarget >= 0) {

                                when (target.id) {
                                    rvTodo -> listener.addDataAtInx(
                                        ProgressType.TO_DO,
                                        positionTarget,
                                        sourceData
                                    )
                                    rvInProgress -> listener.addDataAtInx(
                                        ProgressType.IN_PROGRESS,
                                        positionTarget,
                                        sourceData
                                    )
                                    rvDone -> listener.addDataAtInx(
                                        ProgressType.DONE,
                                        positionTarget,
                                        sourceData
                                    )
                                }
                            } else {
                                when (target.id) {
                                    rvTodo -> listener.addDataAtEnd(ProgressType.TO_DO, sourceData)
                                    rvInProgress -> listener.addDataAtEnd(
                                        ProgressType.IN_PROGRESS,
                                        sourceData
                                    )
                                    rvDone -> listener.addDataAtEnd(ProgressType.DONE, sourceData)
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