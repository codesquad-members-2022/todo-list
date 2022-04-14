package com.codesquad.aos.todolist.ui

import com.codesquad.aos.todolist.data.model.Card

interface DataChangeListener {
    fun swapData(rvType: Int, sourceIndex: Int, targetIndex: Int)
    fun addDataAtEnd(rvType: Int, card: Card)
    fun addDataAtInx(rvType: Int, tartgetIndex: Int, card: Card)
    fun deleteData(rvType: Int, targetIndex: Int)
}