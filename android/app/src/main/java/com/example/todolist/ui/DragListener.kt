package com.example.todolist.ui

import android.view.DragEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R

class DragListener : View.OnDragListener {

    override fun onDrag(view: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                var positionTarget = -1
                val viewSource = event.localState as View?
                val viewId = view.id
                val taskItem = R.id.cl_main
                val rvTodo = R.id.rv_todo
                val rvInProgress = R.id.rv_in_progress
                val rvDone = R.id.rv_done

                when (viewId) {
                    taskItem, rvTodo, rvInProgress, rvDone -> {
                        val target: RecyclerView
                        when (viewId) {
                            rvTodo -> target = view.rootView.findViewById(rvTodo) as RecyclerView
                            rvInProgress -> target =
                                view.rootView.findViewById(rvInProgress) as RecyclerView
                            rvDone -> target = view.rootView.findViewById(rvDone) as RecyclerView
                            else -> {
                                target = view.parent as RecyclerView
                                positionTarget = view.tag as Int
                            }
                        }

                        if (viewSource != null) {
                            val source = viewSource.parent as RecyclerView
                            val adapterSource = source.adapter as TaskAdapter
                            val adapterTarget = target.adapter as TaskAdapter
                            val positionSource = source.getChildAdapterPosition(viewSource)
                            val sourceData = adapterSource.currentList[positionSource]
                            if (adapterSource == adapterTarget) {
                                if (positionTarget < 0) {
                                    adapterSource.remove(positionSource, sourceData)
                                    when (target.id) {
                                        rvTodo -> adapterTarget.add(1, -1, sourceData)
                                        rvInProgress -> adapterTarget.add(2, -1, sourceData)
                                        rvDone -> adapterTarget.add(3, -1, sourceData)
                                    }
                                    return true
                                }

                                val targetPosition = target.getChildAdapterPosition(view)
                                adapterTarget.onItemMove(positionSource, targetPosition)
                                return true
                            }

                            adapterSource.remove(positionSource, sourceData)
                            if (positionTarget >= 0) {
                                when (target.id) {
                                    rvTodo -> adapterTarget.add(1, positionTarget, sourceData)
                                    rvInProgress -> adapterTarget.add(2, positionTarget, sourceData)
                                    rvDone -> adapterTarget.add(3, positionTarget, sourceData)
                                }
                            } else {
                                when (target.id) {
                                    rvTodo -> adapterTarget.add(1, -1, sourceData)
                                    rvInProgress -> adapterTarget.add(2, -1, sourceData)
                                    rvDone -> adapterTarget.add(3, -1, sourceData)
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