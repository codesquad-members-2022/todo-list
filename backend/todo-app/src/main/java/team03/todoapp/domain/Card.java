package team03.todoapp.domain;

import java.time.LocalDateTime;

public class Card {

    private Long cardId;
    private String title;
    private String content;
    private String writer;
    private String currentLocation;
    private LocalDateTime uploadDate;
    private Long nextId;
    private Boolean deleted;

    public Card(String title, String content, String writer, String currentLocation,
        LocalDateTime uploadDate, Long nextId, Boolean deleted) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.currentLocation = currentLocation;
        this.uploadDate = uploadDate;
        this.nextId = nextId;
        this.deleted = deleted;
    }

    public Long getCardId() {
        return cardId;
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

}
