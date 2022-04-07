package kr.codesquad.todolist.dto;

import java.time.LocalDateTime;

public class CardResponse {

    private final Long id;
    private final Integer columnId;
    private final String author;
    private final String subject;
    private final String contents;
    private final LocalDateTime create_time;

    public CardResponse(Long id, Integer columnId, String author, String subject, String contents, LocalDateTime create_time) {
        this.id = id;
        this.columnId = columnId;
        this.author = author;
        this.subject = subject;
        this.contents = contents;
        this.create_time = create_time;
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

    public LocalDateTime getCreate_time() {
        return create_time;
    }
}
