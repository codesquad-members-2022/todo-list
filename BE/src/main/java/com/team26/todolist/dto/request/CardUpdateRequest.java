package com.team26.todolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class CardUpdateRequest {
    private Long id;
    private String title;
    private String contents;
}
