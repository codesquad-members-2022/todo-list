package com.example.backend.web.dto;

public class CardListResponseDto {
    private String title;
    private String content;
    private String authorSystem;

    public CardListResponseDto(String title, String content, String authorSystem) {
        this.title = title;
        this.content = content;
        this.authorSystem = authorSystem;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthorSystem() {
        return authorSystem;
    }
}
