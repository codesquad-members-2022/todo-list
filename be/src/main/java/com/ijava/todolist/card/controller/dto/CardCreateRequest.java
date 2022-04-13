package com.ijava.todolist.card.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CardCreateRequest {
    private Long columnId;
    private String title;
    private String content;

    public CardCreateRequest(Long columnId, String title, String content) {
        this.columnId = columnId;
        this.title = title;
        this.content = content;
    }
}
