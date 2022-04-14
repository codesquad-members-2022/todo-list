package com.todolist.dto;

import lombok.Getter;

@Getter
public class ModifiedWorkDto {

    private String title;
    private String content;

    public ModifiedWorkDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
