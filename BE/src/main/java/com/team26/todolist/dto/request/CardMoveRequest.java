package com.team26.todolist.dto.request;

public class CardMoveRequest {
    private Long id;
    private String newStatus;

    public Long getId() {
        return id;
    }

    public String getNewStatus() {
        return newStatus;
    }
}
