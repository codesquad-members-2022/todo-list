package com.team26.todolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CardResponse {
    private Long id;
    private String userId;
    private String title;
    private String contents;
    private String cardStatusName;
}
