package com.team26.todolist.dto.request;

public class CardRegistrationRequest {
    private String title;
    private String contents;
    private String cardStatus;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getCardStatus() {
        return cardStatus;
    }
}
