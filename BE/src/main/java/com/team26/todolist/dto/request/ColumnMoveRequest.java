package com.team26.todolist.dto.request;

import com.team26.todolist.domain.Column;

public class ColumnMoveRequest {

    private Long id;
    private Long leftColumnId;
    private Long rightColumnId;

    public Long getId() {
        return id;
    }

    public Long getLeftColumnId() {
        return leftColumnId;
    }

    public Long getRightColumnId() {
        return rightColumnId;
    }

    public Column toEntity() {
        return Column.builder().id(id).build();
    }

    @Override
    public String toString() {
        return "ColumnMoveRequest{" +
                "id=" + id +
                ", orderOfLeftColumn=" + leftColumnId +
                ", orderOfRightColumn=" + rightColumnId +
                '}';
    }
}
