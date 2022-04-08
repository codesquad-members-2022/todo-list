package com.todolist.project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardAddDto {
    private String title;
    private String contents;
    private String writer;
}
