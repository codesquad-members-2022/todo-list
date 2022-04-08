package team03.todoapp.domain;

public class Card {

    private Long cardId;
    private String title;
    private String content;
    private String writer;
    private String currentLocation;
    private String uploadDate;
    private Long nextId;
    private String deleted;

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

    public String getUploadDate() {
        return uploadDate;
    }

    public Long getNextId() {
        return nextId;
    }

    public String getDeleted() {
        return deleted;
    }

    public Card(String title, String content, String writer, String currentLocation, String uploadDate, Long nextId, String deleted) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.currentLocation = currentLocation;
        this.uploadDate = uploadDate;
        this.nextId = nextId;
        this.deleted = deleted;
    }
}
