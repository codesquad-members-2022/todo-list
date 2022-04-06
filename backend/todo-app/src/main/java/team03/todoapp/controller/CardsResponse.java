package team03.todoapp.controller;

import lombok.Getter;

@Getter
public class CardsResponse {

    public CardsResponse(Long cardId, String title, String content, String writer,
        Long itemId, Long nextId, String uploadDate) {
        this.cardId = cardId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.itemId = itemId;
        this.nextId = nextId;
        this.uploadDate = uploadDate;
    }

    private Long cardId;
    private String title;
    private String content;
    private String writer;
    private Long itemId;
    private Long nextId;
    private String uploadDate;
}
