package team03.todoapp.domain;

import java.time.LocalDateTime;

public class Card {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String currentLocation;
    private LocalDateTime uploadDate;
    private Long nextId;
    private Boolean deleted;

    public Card(String title, String content, String writer, String currentLocation,
        LocalDateTime uploadDate, Long nextId, Boolean deleted) {
        this(null, title, content, writer, currentLocation, uploadDate, nextId, deleted);
    }

    public Card(Long id, String title, String content, String writer, String currentLocation,
        LocalDateTime uploadDate, Long nextId, Boolean deleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.currentLocation = currentLocation;
        this.uploadDate = uploadDate;
        this.nextId = nextId;
        this.deleted = deleted;
    }

    public Long getCardId() {
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

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public Long getNextId() {
        return nextId;
    }

    public Boolean getDeleted() {
        return deleted;
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
            ", uploadDate=" + uploadDate +
            ", nextId=" + nextId +
            ", deleted=" + deleted +
            '}';
    }
}
