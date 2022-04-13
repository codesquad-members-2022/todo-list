package todo.list.service.dto;

import todo.list.domain.Author;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;

public class CardModifyRequest {

    private Long id;
    private String title;
    private String contents;
    private Author author;
    private CardStatus status;

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

    public CardStatus getStatus() {
        return status;
    }

    public Card toEntity() {
        return new Card(id, title, contents, status, author);
    }
}
