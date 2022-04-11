package com.todolist.project.web.dto;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CardAddDto {
    private String title;
    private String contents;
    private String writer;

    public Card toEntity() {
        return new Card(title, contents, writer);
    }
}
