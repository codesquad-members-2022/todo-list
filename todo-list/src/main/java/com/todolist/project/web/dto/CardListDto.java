package com.todolist.project.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardListDto {
    private Long id;
    private int cardIndex;
    private String title;
    private String contents;
    private String writer;
    private String cardStatus;
    private LocalDateTime createdTime;

}



