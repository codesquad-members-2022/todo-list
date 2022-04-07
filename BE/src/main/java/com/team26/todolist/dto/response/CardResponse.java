package com.team26.todolist.dto.response;

public class CardResponse {
    private Long id;
    private String userId;
    private String title;
    private String contents;
    private String cardStatusName;

    public CardResponse() {
    }

    public CardResponse(Long id, String userId, String title, String contents, String cardStatusName) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.cardStatusName = cardStatusName;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getCardStatusName() {
        return cardStatusName;
    }
}
