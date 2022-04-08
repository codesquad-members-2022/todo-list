package com.codesquad.todolist.card.dto;

import com.codesquad.todolist.card.Card;
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
        this.content = content;
        this.author = author;
    }

    public Card toEntity(int order) {
        return new Card(columnId, title, content, author, order);
    }

    public Integer getColumnId() {
        return columnId;
    }

    public String getTitle() {
        return title;
    }

    // 카드 생성시 content는 필수값이 아님
    public Optional<String> getContent() {
        return Optional.ofNullable(content);
    }

    public String getAuthor() {
        return author;
    }

}
