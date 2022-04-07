package com.example.backend.web.dto;

public class CardsUpdateRequestDto {
    private String title;
    private String content;

    public CardsUpdateRequestDto(String title, String content) {
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
