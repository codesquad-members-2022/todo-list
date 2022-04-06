package com.todolist.project.web.dto;

import com.todolist.project.domain.Status;

public class addCardDto {
    private String title;
    private String contents;
    private String writer;
    private Status status;

    public addCardDto(String title, String contents, String writer, Status status) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
        this.status = status;
    }
}
