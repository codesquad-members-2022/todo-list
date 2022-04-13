package com.team26.todolist.dto.request;

import com.team26.todolist.domain.Column;

public class ColumnRegistrationRequest {

    private String title;

    public Column toEntity() {
        return Column.builder().title(title).build();
    }

    public String getTitle() {
        return title;
    }
}
