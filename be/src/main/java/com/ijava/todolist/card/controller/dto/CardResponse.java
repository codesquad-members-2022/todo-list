package com.ijava.todolist.card.controller.dto;

import com.ijava.todolist.card.domain.Card;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString
@Getter @Setter
@NoArgsConstructor
public class CardResponse {
    private Long cardId;
    private String title;
    private String content;
    private LocalDateTime createdDate;

    public static CardResponse from(Card card) {
        CardResponse response = new CardResponse();
        response.setCardId(card.getId());
        response.setTitle(card.getTitle());
        response.setContent(card.getContent());
        response.setCreatedDate(card.getCreatedDate());
        return response;
    }
}


