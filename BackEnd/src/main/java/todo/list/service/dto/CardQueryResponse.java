package todo.list.service.dto;

import todo.list.domain.Author;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;

public class CardQueryResponse {

    private Long id;
    private CardStatus status;
    private String title;
    private String contents;
    private Author author;

    public CardQueryResponse(Card card) {
        this.id = card.getId();
        this.status = card.getStatus();
        this.title = card.getTitle();
        this.contents = card.getContents();
        this.author = card.getAuthor();
    }

    public Long getId() {
        return id;
    }

    public CardStatus getStatus() {
        return status;
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
}
