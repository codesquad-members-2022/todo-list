package com.todolist.project.domain.card;

import com.todolist.project.domain.CardStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Card {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime createdTime;
    private CardStatus cardStatus;

    public Card(String title, String contents, String writer, CardStatus cardStatus) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.cardStatus = cardStatus;
        this.createdTime = LocalDateTime.now();
    }

    public Card(Long id, String title, String contents, String writer, LocalDateTime createdTime, CardStatus cardStatus) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.cardStatus = cardStatus;
        this.createdTime = createdTime;
    }

}
