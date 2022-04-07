package com.example.backend.web.dto;

public class CardUpdateRequestDto {
    private String title;
    private String content;

    public CardUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
