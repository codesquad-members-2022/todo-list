package todo.list.service.dto;

import todo.list.domain.Author;
import todo.list.domain.Card;

import java.time.LocalDateTime;

public class CardModifyResponse {
    private Long id;
    private String title;
    private String contents;
    private Author author;
    private LocalDateTime updateDateTime;

    public CardModifyResponse(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.contents = card.getContents();
        this.author = card.getAuthor();
        this.updateDateTime = card.getUpdateDateTime();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Author getAuthor() {
        return author;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
