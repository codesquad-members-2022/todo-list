package com.example.todolist.data

class CardRepository(private val cardDataSource: CardRemoteDataSource) {

    suspend fun getCards(): Data? {
        val cards = cardDataSource.getCards()
        return cards.body()?.data
    }

    suspend fun addCard(subject: String, content: String, status: String) : Card? {
        return cardDataSource.addCard(NewCard(subject, content, status)).body()
    }

    fun deleteCard(cardId: Int) {

    }

    fun dropCard(cardId: Int?, order: Int?, status: String?) {

    }

}