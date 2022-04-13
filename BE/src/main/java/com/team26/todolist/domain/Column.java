package com.team26.todolist.domain;

import java.time.LocalDateTime;

public class Column {

    private Long id;
    private String title;
    private Double order;
    private LocalDateTime createdAt;
    private boolean isDeleted;

    public Column(ColumnBuilder columnBuilder) {
        this.id = columnBuilder.id;
        this.title = columnBuilder.title;
        this.order = columnBuilder.order;
        this.createdAt = columnBuilder.createdAt;
        this.isDeleted = columnBuilder.isDeleted;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getOrder() {
        return order;
    }

    public static ColumnBuilder builder() {
        return new ColumnBuilder();
    }

    public static class ColumnBuilder {

        private Long id;
        private String title;
        private Double order;
        private LocalDateTime createdAt;
        private boolean isDeleted;

        public ColumnBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ColumnBuilder title(String title) {
            this.title = title;
            return this;
        }

        public ColumnBuilder order(Double order) {
            this.order = order;
            return this;
        }

        public ColumnBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ColumnBuilder isDeleted(boolean deleted) {
            isDeleted = deleted;
            return this;
        }

        public Column build() {
            return new Column(this);
        }
    }
}
