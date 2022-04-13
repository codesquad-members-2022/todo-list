package com.codesquad.aos.todolist.repository

import com.codesquad.aos.todolist.data.model.Card

interface CardItemDataSource {

    suspend fun getCardItem(): List<Card>
}