package com.team26.todolist.domain;

import com.team26.todolist.util.Constant;
import java.time.LocalDateTime;
import java.util.Objects;

public class Column implements Comparable<Column> {

    private Long id;
    private final String title;
    private Double order;
    private final LocalDateTime createdAt;
    private final boolean isDeleted;

    public Column(ColumnBuilder columnBuilder) {
        this.id = columnBuilder.id;
        this.title = columnBuilder.title;
        this.order = columnBuilder.order;
        this.createdAt = columnBuilder.createdAt;
        this.isDeleted = columnBuilder.isDeleted;
    }

    public static ColumnBuilder builder() {
        return new ColumnBuilder();
    }

    @Override
    public int compareTo(Column column) {
        return Double.compare(getOrder(), column.getOrder());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Double getOrder() {
        return order;
    }

    public void setNewOrder(Column leftColumn, Column rightColumn) {
        if (leftColumn == null && rightColumn == null) {
            order = 0.0;
            return;
        }

        if (leftColumn == null) {
            order = rightColumn.getOrder() - Constant.ORDER_BASIC_DIFFERENCE;
            return;
        }

        if (rightColumn == null) {
            order = leftColumn.getOrder() + Constant.ORDER_BASIC_DIFFERENCE;
            return;
        }

        order = (leftColumn.getOrder() + rightColumn.getOrder()) / 2;
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

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (!(obj instanceof Column)) {
                return false;
            }

            Column column = (Column) obj;
            return this.id == column.id;
        }
    }
}
