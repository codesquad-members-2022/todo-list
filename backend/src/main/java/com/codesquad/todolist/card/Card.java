package com.codesquad.todolist.card;

import java.time.LocalDateTime;

public class Card {

    private final Integer columnId;
    private final String title;
    private final String author;
    private Integer cardId;
    private String content;
    private Integer cardOrder;
    private LocalDateTime createdDate;
    private Boolean deleted;

    public Card(Builder builder) {
        this.cardId = builder.cardId;
        this.columnId = builder.columnId;
        this.title = builder.title;
        this.author = builder.author;
        this.content = builder.content;
        this.cardOrder = builder.cardOrder;
        this.createdDate = builder.createdDate;
        this.deleted = builder.deleted;
    }

    public Integer getCardId() {
        return cardId;
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
        private Integer cardOrder = 1;
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

        public Builder cardOrder(Integer cardOrder) {
            this.cardOrder = cardOrder;
            return this;
        }

        public Builder deleted(Boolean deleted) {
            this.deleted = deleted;
            return this;
        }

        public Card build() {
            return new Card(this);
        }

    }
}
