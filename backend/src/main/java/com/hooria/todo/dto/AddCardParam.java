package com.hooria.todo.dto;

import com.hooria.todo.domain.Card;

public class AddCardParam {

    private final String status;
    private final String title;
    private final String content;
    private final String userId;
    private final String device;

    public AddCardParam(String status, String title, String content, String userId,
        String device) {
        this.status = status;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.device = device;
    }

    public Card toEntity() {
        return Card.of(status, title, content, userId, device, 0);
    }
}
