package com.team26.todolist.dto.request;

public class CardUpdateRequest {
    private Long id;
    private String title;
    private String contents;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
