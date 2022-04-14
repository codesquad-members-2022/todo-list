package com.ijava.todolist.card.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ijava.todolist.card.domain.Card;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class CardResponse {
    private Long cardId;
    private Long columnId;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedDate;

    public static CardResponse from(Card card) {
        CardResponse response = new CardResponse();
        response.cardId = card.getId();
        response.columnId = card.getColumnsId();
        response.title = card.getTitle();
        response.content = card.getContent();
        response.createdDate = card.getCreatedDate();
        response.modifiedDate = card.getModifiedDate();
        return response;
    }
}


