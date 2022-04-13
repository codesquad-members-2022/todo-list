package com.ijava.todolist.card.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardUpdateRequest {
    private String title;
    private String content;

    public CardUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
