package com.team26.todolist.dto.request;

import com.team26.todolist.domain.Column;

public class ColumnMoveRequest {

    private Long id;
    private Double orderOfLeftColumn;
    private Double orderOfRightColumn;

    public Long getId() {
        return id;
    }

    public Double getOrderOfLeftColumn() {
        return orderOfLeftColumn;
    }

    public Double getOrderOfRightColumn() {
        return orderOfRightColumn;
    }

    public Column toEntity() {
        return Column.builder().id(id).build();
    }

    @Override
    public String toString() {
        return "ColumnMoveRequest{" +
                "id=" + id +
                ", orderOfLeftColumn=" + orderOfLeftColumn +
                ", orderOfRightColumn=" + orderOfRightColumn +
                '}';
    }
}
