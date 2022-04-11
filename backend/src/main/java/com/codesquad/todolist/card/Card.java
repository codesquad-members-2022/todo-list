package com.codesquad.todolist.card;

import java.time.LocalDateTime;

public class Card {

    private Integer cardId;
    private Integer columnId;
    private String title;
    private String content;
    private String author;
    private Integer order;
    private LocalDateTime createdDateTime;
    private Boolean deleted;

    public Card(Integer columnId, String title, String content, String author, Integer order) {
        this(null, columnId, title, content, author, order, LocalDateTime.now());
    }

    public Card(Integer cardId, Integer columnId, String title, String content, String author,
        Integer order, LocalDateTime createdDateTime) {
        this.cardId = cardId;
        this.columnId = columnId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.order = order;
        this.createdDateTime = createdDateTime;
    }

    public void update(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Integer getCardId() {
        return cardId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getOrder() {
        return order;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

}
