package com.codesquad.aos.todolist.ui

import com.codesquad.aos.todolist.data.model.Card

interface DataChangeListener {
    fun swapData(rvType: Int, cardId: Int, nextOrder: Int, preOrder: Int, nextSection: String, prevSection: String)
    // fun addDataAtEnd(rvType: Int, card: Card)
    fun addDataAtInx(rvType: Int, cardId: Int, nextOrder: Int, preOrder: Int, nextSection: String, prevSection: String)
    fun deleteData(rvType: Int, targetIndex: Int)
}