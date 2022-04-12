package com.codesquad.aos.todolist.ui

import android.util.Log
import android.view.DragEvent
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.ui.adapter.TodoCardListAdapter
import java.util.*

class DragListener(private val viewModel: TodoViewModel) : View.OnDragListener {
    private var isDropped = false
    override fun onDrag(view: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                isDropped = true
                var positionTarget = -1
                val viewSource = event.localState as View? // 포인터로 잡은 뷰
                val viewId = view.id  // view= 놓는 위치
                val cardItem = R.id.mcItemView
                val rvTodo = R.id.rvTodo
                val rvProgress = R.id.rvProgress
                val rvComplete = R.id.rvComplete
                when (viewId) {
                    cardItem, rvTodo, rvProgress, rvComplete -> {
                        val target: RecyclerView
                        when (viewId) {  // 놓는 위치에 해당하는 리사이클러뷰가 target 이 된다
                            rvTodo -> target = view.rootView.findViewById(rvTodo) as RecyclerView
                            rvProgress -> target =
                                view.rootView.findViewById(rvProgress) as RecyclerView
                            rvComplete -> target =
                                view.rootView.findViewById(rvComplete) as RecyclerView
                            else -> {  // 세 개의 리사이클러뷰를 제외한 위치에 놓았을 때, 카드에 겹치게 놓는 경우,  한 리사이클러뷰 내의 이동만 해당
                                target = view.parent as RecyclerView
                                positionTarget = view.tag as Int
                            }
                        }
                        if (viewSource != null) {
                            val source = viewSource.parent as RecyclerView // 선택한 아이템의 리사이클러뷰
                            val adapterSource = source.adapter as TodoCardListAdapter // 위 리사이클러뷰의 어댑터를 가져옴 <- 이동하려는 것의 어댑터
                            val adapterTarget = target.adapter as TodoCardListAdapter // 이동을 하고 놓을 곳의 어댑터
                            val positionSource = source.getChildAdapterPosition(viewSource) // 이동하려는 아이템뷰의 현재 인덱스?
                            val list = adapterSource.currentList[positionSource] // 이동하려는 아이템뷰의 객체 데이터 <- Card
                            val listSource = adapterSource.currentList.toMutableList() // submitList를 위함, listSource는 이동하려는 아이템뷰의 리사이클러뷰의 전체 리스트

                            if (adapterSource == adapterTarget) {  // 같은 리사이클러뷰 내에서의 이동인 경우 <- 이 부분 없으면 이전처럼 복제 오류 발생
                                if (positionTarget < 0) { // 탈출, 아무 변화가 없다
                                    return true
                                }
                                val targetPosition = target.getChildAdapterPosition(view)  // 드래그 하고 카드 아이템뷰 위에 놓은 경우 여기로 온다,
                                // 여기까지 왔으면 view 는 놓는 리사이클러뷰의 itemView 중 하나
                                Collections.swap(listSource, positionSource, targetPosition)
                                // 같은 리사이클러뷰 내에서 순서만 변경, listSource의 positionSource와 targetPosition 인덱스에 해당하는 두 데이터의 위치를 서로 변경
                                adapterTarget.submitList(listSource) // 바뀐 순서 반영되도록 submitList 호출
                                return true
                            }
                            listSource.removeAt(positionSource)  // 선택한 아이템뷰의 리사이클러뷰가 아닌 다른 리사이클러뷰로 이동하는 경우  이동하려는 아이템뷰를 우선 삭제
                            listSource.let { adapterSource.submitList(it) }
                            val targetList = adapterTarget.currentList.toMutableList()
                            if (positionTarget >= 0) {  // 다른 리사이클러뷰의 아이템뷰 위에 놓았을 때
                                //list.let { targetList.add(positionTarget, it) }
                                list.let { targetList.add(target.getChildAdapterPosition(view), it) }
                            } else {
                                list.let { targetList.add(it) }  //그냥 아이템뷰 없는 공간에 놓았을 때
                            }
                            targetList.let { adapterTarget.submitList(it) } // 타겟 리사이클러뷰 업데이트
                        }
                    }
                }
            }
        }
        if (!isDropped && event.localState != null) {  // 테스틍 용 부분
            val shadowCard = event.localState as View
//            shadowCard.visibility = View.VISIBLE
        }
        return true
    }

}