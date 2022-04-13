package com.team26.todolist.dto.response;

import com.team26.todolist.domain.Column;

public class ColumnResponse {
    private Long id;
    private String title;

    public ColumnResponse() {
    }

    public ColumnResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static ColumnResponse of(Column column) {
        return new ColumnResponse(column.getId(), column.getTitle());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
