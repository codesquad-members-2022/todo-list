package todo.list.service.dto;

import todo.list.domain.Author;
import todo.list.domain.Card;
import todo.list.domain.CardStatus;

public class CardSaveRequest {

    private String title;
    private String contents;
    private CardStatus status;
    private Author author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Card toEntity() {
        return new Card(title, contents, status, author);
    }
}
