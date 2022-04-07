package team03.todoapp.controller.dto;

public class CardResponse {

    private Long cardId;
    private String title;
    private String content;
    private String writer;
    private Long nextId;
    private String uploadDate;

    public CardResponse(Long cardId, String title, String content, String writer, Long nextId, String uploadDate) {
        this.cardId = cardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.nextId = nextId;
        this.uploadDate = uploadDate;
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

    public Long getNextId() {
        return nextId;
    }

    public String getUploadDate() {
        return uploadDate;
    }

}
