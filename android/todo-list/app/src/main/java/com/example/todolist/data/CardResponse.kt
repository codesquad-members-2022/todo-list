package com.example.todolist.data


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CardResponse(
    @SerializedName("data")
    val `data`: Data?
)

data class Data(
    @SerializedName("completed")
    val completed: Completed?,
    @SerializedName("ongoing")
    val ongoing: Ongoing?,
    @SerializedName("todo")
    val todo: Todo?
)

data class Todo(
    @SerializedName("cards")
    val cards: List<Card>?,
    @SerializedName("count")
    val count: Int?
)

data class Ongoing(
    @SerializedName("cards")
    val cards: List<Card>?,
    @SerializedName("count")
    val count: Any?
)

data class Completed(
    @SerializedName("cards")
    val cards: List<Card>?,
    @SerializedName("count")
    val count: Any?
)

data class Card(
    @SerializedName("cardId")
    val cardId: Int?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("subject")
    val subject: String?,
    @SerializedName("userId")
    val userId: Int?
) : Serializable