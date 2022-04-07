package kr.codesquad.todolist.dto;

import kr.codesquad.todolist.domain.Card;

import java.time.LocalDateTime;

public class CardDto {

    private final String author;
    private final Integer columnId;
    private final String subject;
    private final String contents;


    public CardDto(String author, Integer columnId, String subject, String contents) {
        this.author = author;
        this.columnId = columnId;
        this.subject = subject;
        this.contents = contents;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public String getSubject() {
        return subject;
    }

    public String getContents() {
        return contents;
    }

    public Card toEntity() {
        return Card.of(this.author, this.columnId, this.subject, this.contents);
    }
}
