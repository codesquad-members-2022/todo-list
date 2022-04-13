package com.team26.todolist.dto.response;

import com.team26.todolist.domain.Card;

public class CardResponse {
    private Long id;
    private String userId;
    private String title;
    private String contents;
    private Long columnId;

    public CardResponse() {
    }

    public CardResponse(Long id, String userId, String title, String contents, Long columnId) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.contents = contents;
        this.columnId = columnId;
    }

    public static CardResponse of(Card card) {
        return new CardResponse(
                card.getId(),
                card.getUserId(),
                card.getTitle(),
                card.getContents(),
                card.getColumnId()
        );
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

    public Long getColumnId() {
        return columnId;
    }
}
