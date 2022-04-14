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
    val onGoingCardList: LiveData<List<Card>> = _onGoingCardList

    private val _completeCardList = MutableLiveData<List<Card>>()
    val completeCardList: LiveData<List<Card>> = _completeCardList

    private var _actionStatus = ActionStatus.NONE
    val actionStatus: ActionStatus
        get() = _actionStatus

    init {
        viewModelScope.launch {
            val cards = cardRepository.getCards()

            cards?.todo?.cards?.let { setTodoList(it) }
            cards?.ongoing?.cards?.let { setOngoingList(it) }
            cards?.completed?.cards?.let { setCompletedList(it) }
        }
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
                    newCards.let { _todoCardList.value = it }
                }

                "ongoing" -> {
                    val newCards = _onGoingCardList.value?.toMutableList()
                    newCards?.add(card)
                    newCards.let { _onGoingCardList.value = it }
                }

                "completed" -> {
                    val newCards = _completeCardList.value?.toMutableList()
                    newCards?.add(card)
                    newCards.let { _completeCardList.value = it }
                }
            }
        }
    }

    override fun deleteCard(cardId: Int) {
        cardRepository.deleteCard(cardId)
    }

    override fun dropCard(draggedCard: Card, targetCard: Card) {
        cardRepository.dropCard(draggedCard.cardId, targetCard.order, targetCard.status)
    }


}