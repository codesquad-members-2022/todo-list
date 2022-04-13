package com.team26.todolist.dto.request;

import com.team26.todolist.domain.Column;

public class ColumnUpdateRequest {

    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Column toEntity() {
        return Column.builder().id(id).title(title).build();
    }
}
