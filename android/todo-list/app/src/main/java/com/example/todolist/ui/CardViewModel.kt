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

class CardViewModel : ViewModel(), CardActionHandler {

    private val cardRepository = CardRepository(CardRemoteDataSource(ApiClient.create()))

    private val _todoCardList = MutableLiveData<List<Card>>()
    val todoCardList: LiveData<List<Card>> = _todoCardList

    private val _onGoingCardList = MutableLiveData<List<Card>>()
    val ongoingCardList: LiveData<List<Card>> = _onGoingCardList

    private val _completeCardList = MutableLiveData<List<Card>>()
    val completedCardList: LiveData<List<Card>> = _completeCardList

    private var _actionStatus = ActionStatus.NONE
    val actionStatus: ActionStatus
        get() = _actionStatus

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        viewModelScope.launch {
            loadCards()
        }
    }

    private suspend fun loadCards() {
        val cards = cardRepository.getCards()
        cards?.todo?.cards?.sortedByDescending { it.order }?.let { setTodoList(it) }
        cards?.ongoing?.cards?.sortedByDescending { it.order }?.let { setOngoingList(it) }
        cards?.completed?.cards?.sortedByDescending { it.order }?.let { setCompletedList(it) }
    }

    private fun setTodoList(cards: List<Card>) {
        _todoCardList.value = cards
    }

    private fun setOngoingList(cards: List<Card>) {
        _onGoingCardList.value = cards
    }

    private fun setCompletedList(cards: List<Card>) {
        _completeCardList.value = cards
    }


    override fun addCard(newCard: NewCard) {
        viewModelScope.launch {
            val card = cardRepository.addCard(newCard.subject, newCard.content, newCard.status)
            when(card?.status) {
                "todo" -> {
                    val newCards = _todoCardList.value?.toMutableList()
                    newCards?.add(card)
                    newCards?.sortByDescending { it.order }
                    newCards.let { _todoCardList.value = it }
                }

                "ongoing" -> {
                    val newCards = _onGoingCardList.value?.toMutableList()
                    newCards?.add(card)
                    newCards?.sortByDescending { it.order }
                    newCards.let { _onGoingCardList.value = it }
                }

                "completed" -> {
                    val newCards = _completeCardList.value?.toMutableList()
                    newCards?.add(card)
                    newCards?.sortByDescending { it.order }
                    newCards.let { _completeCardList.value = it }
                }
            }
            _actionStatus = ActionStatus.ADD
        }
    }

    override fun deleteCard(cardId: Int) {
        viewModelScope.launch {
            cardRepository.deleteCard(cardId)
            loadCards()
            _actionStatus = ActionStatus.DELETE
        }
    }

    override fun dropCard(draggedCard: Card, targetCard: Card) {
        kotlin.runCatching {
            viewModelScope.launch {
                val cardId = draggedCard.cardId?.let { it } ?: throw IllegalArgumentException("cardId is null!")
                val order = targetCard.order?.let { it } ?: throw IllegalArgumentException("order is null!")
                val status = targetCard.status?.let { it } ?: throw IllegalArgumentException("status id null")

                cardRepository.dropCard(cardId, order, status)
                loadCards()
                _actionStatus = ActionStatus.DROP
            }
        }.onFailure {
            handleError(it)
        }
    }

    private fun handleError(exception: Throwable) {
        _error.value = exception.message
    }

}