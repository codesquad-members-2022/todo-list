package com.todolist.project.web.dto;

import com.todolist.project.domain.Status;

public class addCardDto {
    private String title;
    private String contents;
    private String writer;

    public addCardDto(String title, String contents, String writer) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getWriter() {
        return writer;
    }

}
