package todo.list.domain;

import java.time.LocalDateTime;

public class Card {
    private Long id;
    private String title;
    private String contents;
    private CardStatus status;
    private LocalDateTime createDateTime;
    private Author author;

    public Card(Long id, String title, String contents, CardStatus status, LocalDateTime createDateTime, Author author) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.status = status;
        this.createDateTime = createDateTime;
        this.author = author;
    }

    public boolean equalsStatus(CardStatus cardStatus) {
        return status == cardStatus;
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public Author getAuthor() {
        return author;
    }
}
