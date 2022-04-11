package com.todolist.project.web.dto;

import com.todolist.project.domain.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CardUpdateDto {
    private int cardIndex;
    private String title;
    private String contents;
    private CardStatus cardStatus;

    public LocalDateTime updateCardCreatedTime() {
        return LocalDateTime.now();
    }
}
