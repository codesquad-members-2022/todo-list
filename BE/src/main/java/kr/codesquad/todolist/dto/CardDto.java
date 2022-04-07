package kr.codesquad.todolist.dto;

import kr.codesquad.todolist.domain.Card;


public class CardDto {

    private String author;
    private Integer columnId;
    private String subject;
    private String contents;

    private CardDto() {
    }

    public Card toEntity() {
        return Card.of(this.author, this.columnId, this.subject, this.contents);
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

    @Override
    public String toString() {
        return "CardDto{" +
                "author='" + author + '\'' +
                ", columnId=" + columnId +
                ", subject='" + subject + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
