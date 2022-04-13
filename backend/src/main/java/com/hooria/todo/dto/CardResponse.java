package com.hooria.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CardResponse {

    private final long id;
    private final String status;
    private final String title;
    private final String content;
    private final String userId;
    private final String device;
    private final String createdAt;
    private final String modifiedAt;
    private final boolean deletedYn;
    private final int rowPosition;
}
