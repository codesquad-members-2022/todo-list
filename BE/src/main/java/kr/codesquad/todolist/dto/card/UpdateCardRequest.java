package kr.codesquad.todolist.dto.card;

import kr.codesquad.todolist.domain.Card;

public class UpdateCardRequest {

    private Long id;
    private String author;
    private Integer sectionId;
    private String subject;
    private String contents;

    private UpdateCardRequest() {
    }

    public Card toEntity() {
        return Card.updateCard(this.id, this.author, this.sectionId, this.subject, this.contents);
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public String getSubject() {
        return subject;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "updateCardRequest{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", sectionId=" + sectionId +
                ", subject='" + subject + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
