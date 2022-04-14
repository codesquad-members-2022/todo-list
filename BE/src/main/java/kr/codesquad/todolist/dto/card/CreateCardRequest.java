package kr.codesquad.todolist.dto.card;

import kr.codesquad.todolist.domain.Card;


public class CreateCardRequest {

    private String author;
    private Integer sectionId;
    private String subject;
    private String contents;

    public CreateCardRequest() {}

    public CreateCardRequest(String author, Integer sectionId, String subject, String contents) {
        this.author = author;
        this.sectionId = sectionId;
        this.subject = subject;
        this.contents = contents;
    }

    public Card toEntity() {
        return Card.newCard(this.author, this.sectionId, this.subject, this.contents);
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
        return "CreateCardRequest{" +
                "author='" + author + '\'' +
                ", sectionId=" + sectionId +
                ", subject='" + subject + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
