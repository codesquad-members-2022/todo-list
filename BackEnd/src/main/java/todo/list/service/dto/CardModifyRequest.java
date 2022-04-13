package todo.list.service.dto;

import todo.list.domain.Author;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;

public class CardModifyRequest {

    private Long id;
    private String title;
    private String contents;
    private Author author;
    private CardStatus cardStatus;

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
    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public Card toEntity() {
        return new Card(id, title, contents, cardStatus, author);
    }
}
