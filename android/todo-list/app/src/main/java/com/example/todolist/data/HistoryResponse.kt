package com.example.todolist.data


import com.google.gson.annotations.SerializedName

data class HistoryResponse(
    @SerializedName("histories")
    val histories: List<History>?
)

data class History(
    @SerializedName("action")
    val action: String?,
    @SerializedName("cardId")
    val cardId: Int?,
    @SerializedName("cardSubject")
    val cardSubject: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("currentCardStatus")
    val currentCardStatus: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("prevCardStatus")
    val prevCardStatus: String?,
    @SerializedName("userId")
    val userId: Int?
)