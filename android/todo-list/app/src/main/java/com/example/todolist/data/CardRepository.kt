package com.example.todolist.data

class CardRepository(private val cardDataSource: CardRemoteDataSource) {

    suspend fun getCards(): Data? {
        val cards = cardDataSource.getCards()
        return cards.body()?.data
    }

    fun addTodoCard(subject: String, content: String, status: String) {

    }

    fun addOnGoingCard(subject: String, content: String, status: String) {

    }

    fun addCompletedCard(subject: String, content: String, status: String) {

    }

    fun deleteCard(cardId: Int) {

    }

    fun dropCard(cardId: Int?, order: Int?, status: String?) {

    }

}