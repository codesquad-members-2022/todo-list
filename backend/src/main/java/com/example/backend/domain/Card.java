package com.example.backend.domain;

public class Card {
    private Long id;
    private String title;
    private String content;
    private String authorSystem;
    private String status;
    private Long orderIndex;

    public Card(Long id, String title, String content, String authorSystem, String status, Long orderIndex) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorSystem = authorSystem;
        this.status = status;
        this.orderIndex = orderIndex;
    }

    static class Builder {
        private Long id;
        private String title;
        private String content;
        private String authorSystem;
        private String status;
        private Long orderIndex;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder authorSystem(String authorSystem) {
            this.authorSystem = authorSystem;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder orderIndex(Long orderIndex) {
            this.orderIndex = orderIndex;
            return this;
        }

        public Card build() {
            return new Card(id, title, content, authorSystem, status, orderIndex);
        }
    }
}
