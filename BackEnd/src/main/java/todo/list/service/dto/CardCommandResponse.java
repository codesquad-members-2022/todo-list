package todo.list.service.dto;

import todo.list.domain.Author;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;

import java.time.LocalDateTime;

public class CardCommandResponse {

    private Long id;
    private String title;
    private String contents;
    private CardStatus status;
    private Author author;
    private LocalDateTime updateDateTime;

    public CardCommandResponse(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.contents = card.getContents();
        this.status = card.getStatus();
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

    public CardStatus getStatus() {
        return status;
    }

    public Author getAuthor() {
        return author;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }
}
