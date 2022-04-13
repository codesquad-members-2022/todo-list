package com.todolist.dto;

import com.todolist.domain.Work;

public class WorkRequestFormDto {

    private String title;
    private String content;

    public WorkRequestFormDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Work convertToDomain(Integer categoryId, String userId) {
        return new Work(categoryId, title, content, userId);
    }
}
