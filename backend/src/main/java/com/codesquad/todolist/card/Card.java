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
        Integer order, LocalDateTime createdDate) {
        this.cardId = cardId;
        this.columnId = columnId;
        this.title = title;
        this.content = content;
        this.author = author;
        this.order = order;
        this.createdDate = createdDate;
    }

    public Card(Builder builder) {
        this.cardId = builder.cardId;
        this.columnId = builder.columnId;
        this.title = builder.title;
        this.author = builder.author;
        this.content = builder.content;
        this.order = builder.order;
        this.createdDate = builder.createdDate;
        this.deleted = builder.deleted;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public static class Builder {

        private final Integer columnId;
        private final String title;
        private final String author;
        private Integer cardId;
        private String content;
        private Integer order;
        private LocalDateTime createdDate;
        private Boolean deleted = false;

        public Builder(Integer columnId, String title, String author) {
            this.columnId = columnId;
            this.title = title;
            this.author = author;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder date(LocalDateTime dateTime) {
            this.createdDate = dateTime;
            return this;
        }

        public Builder deleted(Boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Builder order(int order) {
            this.order = order;
            return this;
        }

        public Card build() {
            return new Card(this);
        }

    }
}
