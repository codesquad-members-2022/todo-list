package team03.todoapp.domain;

import java.time.LocalDateTime;

public class Card {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String currentLocation;
    private LocalDateTime uploadDateTime;
    private Long nextId;
    private boolean isDeleted;

    public Card(String title, String content, String writer, String currentLocation,
        LocalDateTime uploadDate, Long nextId) {
        this(null, title, content, writer, currentLocation, uploadDate, nextId);
    }

    public Card(Long id, String title, String content, String writer, String currentLocation,
        LocalDateTime uploadDateTime, Long nextId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.currentLocation = currentLocation;
        this.uploadDateTime = uploadDateTime;
        this.nextId = nextId;
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

    public String getCurrentLocation() {
        return currentLocation;
    }

    public LocalDateTime getUploadDateTime() {
        return uploadDateTime;
    }

    public Long getNextId() {
        return nextId;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Card{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", writer='" + writer + '\'' +
            ", currentLocation='" + currentLocation + '\'' +
            ", uploadDate=" + uploadDateTime +
            ", nextId=" + nextId +
            ", isDeleted=" + isDeleted +
            '}';
    }
}
