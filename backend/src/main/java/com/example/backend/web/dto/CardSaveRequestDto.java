package com.example.backend.web.dto;

import com.example.backend.domain.Card;

public class CardSaveRequestDto {
    private String title;
    private String content;
    private String authorSystem;
    private String status;

    public CardSaveRequestDto(String title, String content, String authorSystem, String status) {
        this.title = title;
        this.content = content;
        this.authorSystem = authorSystem;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public Card toEntity() {
        return new Card.Builder()
                .title(title)
                .content(content)
                .authorSystem(authorSystem)
                .status(status)
                .build();
    }
}
