package todo.list.domain;

import java.time.LocalDateTime;

public class Card {
    private Long id;
    private String title;
    private String contents;
    private CardStatus status;
    private Author author;
    private LocalDateTime updateDateTime;

    public Card(Long id, CardStatus status) {
        this(id, null, null, status, LocalDateTime.now(), null);
    }

    public Card(String title, String contents, CardStatus status, Author author) {
        this(null, title, contents, status, LocalDateTime.now(), author);
    }

    public Card(Long id, String title, String contents, Author author) {
        this(id, title, contents, null, null, author);
    }

    public Card(Long id, String title, String contents, CardStatus status, LocalDateTime updateDateTime, Author author) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.status = status;
        this.updateDateTime = updateDateTime;
        this.author = author;
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
