package team03.todoapp.controller.dto;

import java.time.LocalDateTime;

public class CardResponse {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private Long nextId;
    private LocalDateTime uploadDate;

    public CardResponse(Long id, String title, String content, String writer, Long nextId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.nextId = nextId;
        this.uploadDate = LocalDateTime.now();
    }

    //mock 전용 생성자
    public CardResponse(Long id, String title, String content, String writer, Long nextId,
        LocalDateTime uploadDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.nextId = nextId;
        this.uploadDate = uploadDate;
    }

    public Long id() {
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

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

}
