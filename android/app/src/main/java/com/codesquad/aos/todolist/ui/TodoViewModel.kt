package com.codesquad.aos.todolist.ui

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codesquad.aos.todolist.data.model.Card
import com.codesquad.aos.todolist.data.model.Log
import com.codesquad.aos.todolist.data.model.handlecard.AddCard
import com.codesquad.aos.todolist.data.model.handlecard.EditCard
import com.codesquad.aos.todolist.data.model.handlecard.MoveCard
import com.codesquad.aos.todolist.repository.CardItemRepository
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: CardItemRepository) : ViewModel() {

    private val _todoListLd = MutableLiveData<MutableList<Card>>(mutableListOf())
    private val todolist = mutableListOf<Card>()
    val todoListLd: LiveData<MutableList<Card>> get() = _todoListLd

    private val _progressListLd = MutableLiveData<MutableList<Card>>()
    private val progresslist = mutableListOf<Card>()
    val progressListLd: LiveData<MutableList<Card>> get() = _progressListLd

    private val _completeListLd = MutableLiveData<MutableList<Card>>()
    private val completelist = mutableListOf<Card>()
    val completeListLd: LiveData<MutableList<Card>> get() = _completeListLd

    private val _LogListLd = MutableLiveData<MutableList<Log>>()
    private var LogList = mutableListOf<Log>()
    val LogListLd: LiveData<MutableList<Log>> get() = _LogListLd

    private var cardId = 1

    val progressVisible = MutableLiveData<Boolean>(false)

    // 해야할 일 추가
/*    fun addTodo(title: String, content: String){
        todolist.add(0, Card(cardId++, content, 0, "todo", title))
        _todoListLd.value = todolist
    }

    fun addProgress(title: String, content: String){
        progresslist.add(0, Card(cardId++, content, 0, "doing", title))
        _progressListLd.value = progresslist
    }

    fun addComplete(title: String, content: String){
        completelist.add(0, Card(cardId++, content, 0, "done", title))
        _completeListLd.value = completelist
    }*/

    // Card 객체를 바로 리스트 마지막에 추가
    fun addTodoCard(card: Card) {
        todolist.add(card)
        _todoListLd.value = todolist
    }

    fun addProgressCard(card: Card) {
        progresslist.add(card)
        _progressListLd.value = progresslist
    }

    fun addCompleteCard(card: Card) {
        completelist.add(card)
        _completeListLd.value = completelist
    }

    // Card 객체를 바로 리스트 마지막에 추가
    fun addTodoCardAtInx(targetInx: Int, card: Card) {
        todolist.add(targetInx, card)
        _todoListLd.value = todolist
    }

    fun addProgressCardAtInx(targetInx: Int, card: Card) {
        progresslist.add(targetInx, card)
        _progressListLd.value = progresslist
    }

    fun addCompleteCardAtInx(targetInx: Int, card: Card) {
        completelist.add(targetInx, card)
        _completeListLd.value = completelist
    }

    fun addLog(log: String, time: String) {
        LogList.add(Log("@Han", log, time))
        _LogListLd.value = LogList
    }

    // 삭제
    fun deleteTodo(inx: Int) {
        todolist.removeAt(inx)
        _todoListLd.value = todolist
    }

    fun deleteProgress(inx: Int) {
        progresslist.removeAt(inx)
        _progressListLd.value = progresslist
    }

    fun deleteComplete(inx: Int) {
        completelist.removeAt(inx)
        _completeListLd.value = completelist
    }

    // 한 리사이클러뷰 내에서 순서 변경
    fun changeTodoOrder(fromPos: Int, toPos: Int) {
        /*  val fromTemp = Card(todolist[fromPos].cardId, todolist[fromPos].content, todolist[fromPos].order,
              todolist[fromPos].section, todolist[fromPos].title)
          val toTemp = Card(todolist[toPos].cardId, todolist[toPos].content, todolist[toPos].order,
              todolist[toPos].section, todolist[toPos].title)*/

        val temp = todolist[fromPos]
        todolist[fromPos] = todolist[toPos]
        todolist[toPos] = temp
        _todoListLd.value = todolist
    }

    fun changeProgressOrder(fromPos: Int, toPos: Int) {
        /*val fromTemp = Card(progresslist[fromPos].cardId, progresslist[fromPos].content, progresslist[fromPos].order,
            progresslist[fromPos].section, progresslist[fromPos].title)
        val toTemp = Card(progresslist[toPos].cardId, progresslist[toPos].content, progresslist[toPos].order,
            progresslist[toPos].section, progresslist[toPos].title)*/

        val temp = progresslist[fromPos]
        progresslist[fromPos] = progresslist[toPos]
        progresslist[toPos] = temp
        _progressListLd.value = progresslist
    }

    fun changeCompleteOrder(fromPos: Int, toPos: Int) {
        /*  val fromTemp = Card(completelist[fromPos].cardId, completelist[fromPos].content, completelist[fromPos].order,
              completelist[fromPos].section, completelist[fromPos].title)
          val toTemp = Card(completelist[toPos].cardId, completelist[toPos].content, completelist[toPos].order,
              completelist[toPos].section, completelist[toPos].title)*/

        val temp = completelist[fromPos]
        completelist[fromPos] = completelist[toPos]
        completelist[toPos] = temp
        _completeListLd.value = completelist
    }

    ///////////////

    // 전체 카드 조회
    fun getCardItems() {
        viewModelScope.launch {
            progressVisible.postValue(true)
            val resopnse = repository.getCardItems()

            if (resopnse.isSuccessful) {
                android.util.Log.d("AppTest", "전체 카드 조회 성공, ${resopnse.message()}")
                progressVisible.postValue(false)

                val responseBody = resopnse.body()
                responseBody?.let {
                    todolist.clear()
                    todolist.addAll(it.classifiedCardsDTO.classifiedCards.todo.toMutableList())
                    todolist.reverse()
                    _todoListLd.value = todolist

                    progresslist.clear()
                    progresslist.addAll(it.classifiedCardsDTO.classifiedCards.doing.toMutableList())
                    progresslist.reverse()
                    _progressListLd.value = progresslist

                    completelist.clear()
                    completelist.addAll(it.classifiedCardsDTO.classifiedCards.done.toMutableList())
                    completelist.reverse()
                    _completeListLd.value = completelist
                }
            } else {
                android.util.Log.d("AppTest", "${resopnse.message()}")
                progressVisible.postValue(false)
            }

        }
    }

    // 카드 추가
    fun addCard(title: String, content: String, section: String) {
        progressVisible.postValue(true)

        val sectionSize = when (section) {
            "todo" -> todolist.size
            "doing" -> progresslist.size
            "done" -> completelist.size
            else -> -1
        }
        val addCardData = AddCard(content, sectionSize, section, title)

        viewModelScope.launch {
            val addCardResponse = repository.addCardItem(addCardData)
            if (addCardResponse.isSuccessful) {
                progressVisible.postValue(false)
                android.util.Log.d("AppTest", "카드 추가 성공")

                getCardItems()

                // 카드 추가 성공 판단용 boolean livedata 설정해보기
            } else {
                progressVisible.postValue(false)
                android.util.Log.d("AppTest", "카드 추가 실패")

                // 카드 추가 성공 판단용 boolean livedata 설정해보기
            }
        }

    }

    // 카드 수정
    fun editCard(cardId: Int, content: String, order: Int, section: String, title: String) {
        progressVisible.postValue(true)
        val editCardData = EditCard(content, order, section, title)
        val cardId = cardId

        viewModelScope.launch {
            val editCardResponse = repository.editCardItem(cardId, editCardData)
            if (editCardResponse.isSuccessful) {
                progressVisible.postValue(false)
                getCardItems()
                // 새로 전체 카드 조회하기 -> 수정 사항 업데이트 위함
            } else {
                progressVisible.postValue(false)
            }
        }
    }

    // 카드 삭제
    fun deleteCard(cardId: Int) {
        progressVisible.postValue(true)

        viewModelScope.launch {
            val deleteCardResponse = repository.deleteCardItem(cardId)
            if (deleteCardResponse.isSuccessful) {
                progressVisible.postValue(false)
                getCardItems()
            } else {
                progressVisible.postValue(false)
            }
        }
    }

    // 카드 이동
    fun moveCard(cardId: Int, nextOrder: Int, preOrder: Int, nextSection: String, prevSection: String){
        progressVisible.postValue(true)
        val moveCardData = MoveCard(nextOrder, preOrder, prevSection, nextSection)

        viewModelScope.launch {
            val moveCardResponse = repository.moveCardItem(cardId, moveCardData)
            if(moveCardResponse.isSuccessful){
                progressVisible.postValue(false)
                getCardItems()
            }
            else{
                progressVisible.postValue(false)
            }
        }
    }


}