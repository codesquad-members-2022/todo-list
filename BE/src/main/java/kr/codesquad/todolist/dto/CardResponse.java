package kr.codesquad.todolist.dto;

import kr.codesquad.todolist.domain.Card;

import java.time.LocalDateTime;

public class CardResponse {

    private final Long id;
    private final Integer columnId;
    private final String author;
    private final String subject;
    private final String contents;
    private final LocalDateTime createTime;

    public CardResponse(Long id, Integer columnId, String author, String subject, String contents, LocalDateTime createTime) {
        this.id = id;
        this.columnId = columnId;
        this.author = author;
        this.subject = subject;
        this.contents = contents;
        this.createTime = createTime;
    }

    public static CardResponse from(Card saved) {
        return new CardResponse(saved.getId(), saved.getColumnId(), saved.getUserId(), saved.getSubject(), saved.getContents(), saved.getCreateTime());
    }

    public Long getId() {
        return id;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public String getAuthor() {
        return author;
    }

    public String getSubject() {
        return subject;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
