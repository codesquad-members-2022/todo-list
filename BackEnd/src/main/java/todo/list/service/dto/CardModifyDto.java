package todo.list.service.dto;

import todo.list.domain.Author;
import todo.list.domain.Card;

public class CardModifyDto {

    private Long id;
    private String title;
    private String contents;
    private String author;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getAuthor() {
        return author;
    }

    public Card toEntity() {
        return new Card(id, title, contents, Author.valueOfWithCaseInsensitive(author));
    }
}
