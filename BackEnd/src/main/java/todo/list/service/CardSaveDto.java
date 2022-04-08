package todo.list.service;

import todo.list.domain.Card;
import todo.list.domain.CardStatus;

public class CardSaveDto {

    private String title;
    private String contents;
    private CardStatus status;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public CardStatus getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

//    public Card toEntity() {
////        return new Card();
//    }
}
