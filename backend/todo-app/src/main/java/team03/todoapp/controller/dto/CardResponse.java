package team03.todoapp.controller.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import team03.todoapp.repository.domain.Card;

public class CardResponse {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long nextId;
    private LocalDateTime uploadDateTime;

    public CardResponse(Long id, String title, String content, String writer, Long nextId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.nextId = nextId;
        this.uploadDateTime = LocalDateTime.now();
    }

    //mock 전용 생성자
    public CardResponse(Long id, String title, String content, String writer, Long nextId,
        LocalDateTime uploadDateTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.nextId = nextId;
        this.uploadDateTime = uploadDateTime;
    }

    public CardResponse(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.content = card.getContent();
        this.writer = card.getWriter();
        this.nextId = card.getNextId();
        this.uploadDateTime = card.getUploadDateTime();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public Long getNextId() {
        return nextId;
    }

    public String getUploadDateTime() {
        return uploadDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
