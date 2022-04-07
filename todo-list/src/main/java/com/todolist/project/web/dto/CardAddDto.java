package com.todolist.project.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardAddDto {
    private String title;
    private String contents;
    private String writer;
}
