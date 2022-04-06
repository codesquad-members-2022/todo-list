package com.todolist.project.domain.card;

import com.todolist.project.domain.Status;

import java.time.LocalDateTime;

public class Card {
    private int id;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime createdTime;
    private Status status;

    public Card(String title, String contents, String writer, Status status) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.status = status;
        this.createdTime = LocalDateTime.now();
    }
}
