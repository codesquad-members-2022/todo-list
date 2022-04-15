package com.todolist.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Card {

    private Integer cardId;
    private Integer userId;
    private String cardTitle;
    private String cardContent;
    private String boardName;
    private Long boardIdx;
    private LocalDateTime createdTime;
    private boolean removed;
}
