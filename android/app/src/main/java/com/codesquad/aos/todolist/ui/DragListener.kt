package com.codesquad.aos.todolist.ui

import android.util.Log
import android.view.DragEvent
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.codesquad.aos.todolist.R
import com.codesquad.aos.todolist.ui.adapter.TodoCardListAdapter
import java.util.*

class DragListener(private val dataChangeListener: DataChangeListener) : View.OnDragListener {
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
                        Log.d("AppTest", "rvTodo id : ${rvTodo}, rvProgress id : ${rvProgress}, rvComplete id : ${rvComplete}")
                        Log.d("AppTest", "target id : ${target.id}")
                        if (viewSource != null) {
                            val source = viewSource.parent as RecyclerView // 선택한 아이템의 리사이클러뷰
                            val adapterSource = source.adapter as TodoCardListAdapter // 위 리사이클러뷰의 어댑터를 가져옴 <- 이동하려는 것의 어댑터
                            val adapterTarget = target.adapter as TodoCardListAdapter // 이동을 하고 놓을 곳의 어댑터
                            val positionSource = source.getChildAdapterPosition(viewSource) // 이동하려는 아이템뷰의 현재 인덱스?
                            val sourceData = adapterSource.currentList[positionSource] // 이동하려는 아이템뷰의 객체 데이터 <- Card
                            val listSource = adapterSource.currentList.toMutableList() // submitList를 위함, listSource는 이동하려는 아이템뷰의 리사이클러뷰의 전체 리스트

                            //val targetPosition = target.getChildAdapterPosition(view) // 드래그 하고 카드 아이템뷰 위에 놓은 경우 여기로 온다,
                            if (adapterSource == adapterTarget) {  // 같은 리사이클러뷰 내에서의 이동인 경우 <- 이 부분 없으면 이전처럼 복제 오류 발생
                                if (positionTarget < 0) { // 탈출, 아무 변화가 없다
                                    return true
                                }
                                val targetPosition = target.getChildAdapterPosition(view)  // 드래그 하고 카드 아이템뷰 위에 놓은 경우 여기로 온다,
                                // 여기까지 왔으면 view 는 놓는 리사이클러뷰의 itemView 중 하나
                                //Collections.swap(listSource, positionSource, targetPosition)

                                // 같은 리사이클러뷰 내에서 순서만 변경, listSource의 positionSource와 targetPosition 인덱스에 해당하는 두 데이터의 위치를 서로 변경
                                //adapterTarget.submitList(listSource) // 바뀐 순서 반영되도록 submitList 호출

                                var preOrder = -2
                                var nextOrder = -2
                                var preSection = ""
                                var nextSection = ""
                                val cardId = sourceData.cardId
                                val section = sourceData.section

                                // 같은 리사이클러뷰 내에서 순서를 변경 시 아래에서 위로 이동, 위에서 아래로 이동하는 경우를 나눠서 처리해주기

                                if(positionSource > targetPosition){  // 아래에서 위로 이동 시, db 상에서 드롭 한 카드의 다음에 위치하도록 구현, 화면상에서는 드롭 한 카드의 위에 위치
                                    preOrder = adapterTarget.currentList[targetPosition].order
                                    nextOrder = if(targetPosition == 0){
                                        -1
                                    }else{
                                        adapterTarget.currentList[targetPosition-1].order
                                    }
                                }
                                else if(positionSource < targetPosition){ // 위에서 아래로 이동 시, db 상에서 드롭 한 카드의 전에 위치하도록 구현, 화면상에서는 드롭 한 카드의 밑에 위치
                                    nextOrder = adapterTarget.currentList[targetPosition].order
                                    preOrder = if(targetPosition == adapterTarget.currentList.size - 1){
                                        -1
                                    }
                                    else{
                                        adapterTarget.currentList[targetPosition + 1].order
                                    }
                                }

                                if(positionSource != targetPosition){ // 드래그 후 자기 자신 위치에 놓으면 변화없음
                                    when(target.id){
                                        rvTodo -> dataChangeListener.swapData(1, cardId,  nextOrder, preOrder, section, section)
                                        rvProgress -> dataChangeListener.swapData(2, cardId,  nextOrder, preOrder, section, section)
                                        rvComplete -> dataChangeListener.swapData(3, cardId,  nextOrder, preOrder, section, section)
                                    }
                                }

                                return true
                            }

                            //listSource.removeAt(positionSource)  // 선택한 아이템뷰의 리사이클러뷰가 아닌 다른 리사이클러뷰로 이동하는 경우  이동하려는 아이템뷰를 우선 삭제
                            //listSource.let { adapterSource.submitList(it) }

                            // api 연동 시 이동 전 section, 이동 후 section 값을 주면 알아서 section 이 변경된다,  더미 테스트인 경우에만 직접 삭제를 해줬던 것임
                           /* when(source.id){  // 선택한 아이템뷰의 리사이클러뷰에서는 선택한 것을 삭제해야 한다
                                rvTodo -> dataChangeListener.deleteData(1, positionSource)
                                rvProgress -> dataChangeListener.deleteData(2, positionSource)
                                rvComplete -> dataChangeListener.deleteData(3, positionSource)
                            }*/

                            val targetList = adapterTarget.currentList.toMutableList()
                            if (positionTarget >= 0) {  // 다른 리사이클러뷰의 아이템뷰 위에 놓았을 때
                                //sourceData.let { targetList.add(positionTarget, it) }
                                //sourceData.let { targetList.add(target.getChildAdapterPosition(view), it) }

                                val targetPosition = target.getChildAdapterPosition(view)

                                val preOrder = adapterTarget.currentList[targetPosition].order  // db 상에서 놓은 카드의 다음에 위치시키기 위함,  화면상에서는 놓은 카드의 위에 위치
                                val nextOrder = if(targetPosition == 0){
                                    -1
                                }else{
                                    adapterTarget.currentList[targetPosition-1].order // db 상에서 놓은 카드의 다음에 있던 카드를  이동한 카드의 다음에 위치시키기 위함
                                }

                                val prevSection = sourceData.section
                                val cardId = sourceData.cardId
                                val nextSection = adapterTarget.currentList[targetPosition].section


                                when(target.id){
                                    rvTodo -> dataChangeListener.addDataAtInx(1, cardId, nextOrder, preOrder, nextSection, prevSection)
                                    rvProgress -> dataChangeListener.addDataAtInx(2, cardId, nextOrder, preOrder, nextSection, prevSection)
                                    rvComplete -> dataChangeListener.addDataAtInx(3, cardId, nextOrder, preOrder, nextSection, prevSection)
                                }

                            } else {
                                //sourceData.let { targetList.add(it) }  //  아이템뷰 없는 빈 섹션에 놓았을 때 (빈 리스트)
                               // val targetPosition = target.getChildAdapterPosition(view)  // 이 경우 view가 리사이클러뷰 자체라 왼쪽같이 사용하면 오류 발생!!!

                                var preOrder = -2
                                var nextOrder = -2

                                if(adapterTarget.currentList.size == 0){ // 빈 섹션에 추가 시 pre, next order 모두 -1값을 할당한다
                                    preOrder = -1
                                    nextOrder = -1
                                }
                                else{  // db 상에서 맨 앞에 위치 시키기 위함, 화면상에서는 맨 밑에 위치
                                    preOrder = -1
                                    nextOrder = adapterTarget.currentList[adapterTarget.currentList.size - 1].order
                                }

                                val prevSection = sourceData.section
                                val cardId = sourceData.cardId
                                //val nextSection = adapterTarget.currentList[targetPosition].section

                                val nextSection = when(target.id){ // targetPosition을 사용할 수 없어서 놓는 영역의 리사이클러뷰에 따라 처리
                                    rvTodo -> "todo"
                                    rvProgress -> "doing"
                                    rvComplete -> "done"
                                    else -> ""
                                }

                                when(target.id){
                                    rvTodo -> dataChangeListener.addDataAtInx(1, cardId, nextOrder, preOrder, nextSection, prevSection)
                                    rvProgress -> dataChangeListener.addDataAtInx(2, cardId, nextOrder, preOrder, nextSection, prevSection)
                                    rvComplete -> dataChangeListener.addDataAtInx(3, cardId, nextOrder, preOrder, nextSection, prevSection)
                                }
                            }

                            // targetList.let { adapterTarget.submitList(it) } // 타겟 리사이클러뷰 업데이트
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