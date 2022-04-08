package com.codesquad.todolist.card;

import java.time.LocalDateTime;
import java.util.Optional;

public class CardCreateRequest {

    private final Integer columnId;
    private final String title;
    private final String author;
    private final String content;

    public CardCreateRequest(Integer columnId, String title, String author, String content) {
        this.columnId = columnId;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Card toEntity(int order) {
        return new Card.Builder(this.getColumnId(), this.getTitle(), this.getAuthor())
            .content(this.getContent().orElse(""))
            .date(LocalDateTime.now())
            .order(order)
            .build();
    }

    public Integer getColumnId() {
        return columnId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    // 카드 생성시 content는 필수값이 아님
    public Optional<String> getContent() {
        return Optional.ofNullable(content);
    }
}
