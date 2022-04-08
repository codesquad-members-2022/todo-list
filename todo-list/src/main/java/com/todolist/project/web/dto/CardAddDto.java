package com.todolist.project.web.dto;

import com.todolist.project.domain.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CardAddDto {
    private String title;
    private String contents;
    private String writer;

    public CardStatus createCardStatus() {
        return CardStatus.TODO;
    }

    public LocalDateTime cardCreatedTime() {
        return LocalDateTime.now();
    }
}
