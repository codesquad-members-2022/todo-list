package com.hooria.todo.dto;

import com.hooria.todo.domain.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCardRequest {

    private String status;
    private String title;
    private String content;
    private String userId;
    private String device;

    public Card toEntity() {
        return Card.of(status, title, content, userId, device, 0);
    }
}
