package com.example.todolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.*
import com.example.todolist.network.ApiClient
import com.example.todolist.ui.common.ActionStatus
import com.example.todolist.ui.common.CardActionHandler
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel(), CardActionHandler {

    private val cardRepository = CardRepository(CardRemoteDataSource(ApiClient.create()))

    private val _todoTaskList = MutableLiveData<List<Card>>()
    val todoTaskList: LiveData<List<Card>> = _todoTaskList

    private val _onGoingTaskList = MutableLiveData<List<Card>>()
    val onGoingTaskList: LiveData<List<Card>> = _onGoingTaskList

    private val _completeTaskList = MutableLiveData<List<Card>>()
    val completeTaskList: LiveData<List<Card>> = _completeTaskList

    private var _actionStatus = ActionStatus.NONE
    val actionStatus: ActionStatus
        get() = _actionStatus

    init {
//        loadAllTask()
        viewModelScope.launch {
            val r = cardRepository.getCards()
        }
    }

//    private fun loadAllTask() {
//        loadTodoTask()
//        loadOngoingTask()
//        loadCompletedTask()
//    }
//
//    private fun loadCompletedTask() {
//        _completeTaskList.value = cardRepository.getCompleteTaskList()
//    }
//
//    private fun loadTodoTask() {
//        _todoTaskList.value = cardRepository.getTaskList()
//    }
//
//    private fun loadOngoingTask() {
//        _onGoingTaskList.value = cardRepository.getOnGoingTaskList()
//    }

    override fun addCard(card: NewCard) {
        when(card.status) {
            "todo" -> cardRepository.addTodoCard(card.subject, card.content, card.status)
            "ongoing" -> cardRepository.addOnGoingCard(card.subject, card.content, card.status)
            "complete" -> cardRepository.addCompletedCard(card.subject, card.content, card.status)
        }
    }

    override fun deleteCard(cardId: Int) {
        cardRepository.deleteCard(cardId)
    }

    override fun dropCard(draggedCard: Card, targetCard: Card) {
        cardRepository.dropCard(draggedCard.cardId, targetCard.order, targetCard.status)
    }

}