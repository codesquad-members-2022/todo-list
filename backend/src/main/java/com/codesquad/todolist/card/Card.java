package com.codesquad.todolist.card;

import java.time.LocalDateTime;

public class Card {

    private final Integer columnId;
    private final String title;
    private final String author;
    private Integer cardId;
    private String content;
    private Integer order;
    private LocalDateTime createdDate;
    private Boolean deleted;

    public Card(Integer columnId, String title, String content, String author, Integer order) {
        this(null, columnId, title, content, author, order, LocalDateTime.now());
    }

    public Card(Integer cardId, Integer columnId, String title, String content, String author,
        Integer cardOrder, LocalDateTime createdDate) {
        this.cardId = cardId;
        this.columnId = columnId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.order = cardOrder;
        this.createdDate = createdDate;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
