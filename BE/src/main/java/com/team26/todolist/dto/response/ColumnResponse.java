package com.team26.todolist.dto.response;

import com.team26.todolist.domain.Column;

public class ColumnResponse {
    private Long id;
    private String title;
    private Double order;

    public ColumnResponse() {
    }

    public ColumnResponse(Long id, String title, Double order) {
        this.id = id;
        this.title = title;
        this.order = order;
    }

    public static ColumnResponse of(Column column) {
        return new ColumnResponse(column.getId(), column.getTitle(), column.getOrder());
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
}
