package com.todolist.dto;

import com.todolist.domain.Work;

public class WorkRequestFormDto {

    private String userId;
    private Integer categoryId;
    private String title;
    private String content;

    public WorkRequestFormDto(String userId, Integer categoryId, String title, String content) {
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
    }

    public Work convertToDomain() {
        return new Work(categoryId, title, content, userId);
    }
}
