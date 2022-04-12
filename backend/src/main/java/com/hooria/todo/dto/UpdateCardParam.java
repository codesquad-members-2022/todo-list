package com.hooria.todo.dto;

import com.hooria.todo.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCardParam {

    private String action;
    private long id;
    private String title;
    private String content;
    private String userId;
    private String device;
    private String fromStatus;
    private String toStatus;

    public Card toEntity() {
        return Card.of(id, toStatus, title, content, userId, device);
    }
}
