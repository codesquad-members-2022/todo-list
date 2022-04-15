package kr.codesquad.todolist.dto.card;

import kr.codesquad.todolist.domain.Card;

import java.time.LocalDateTime;

public class CardResponse {

    private final Long id;
    private final String author;
    private final Integer sectionId;
    private final String subject;
    private final String contents;
    private final LocalDateTime createdAt;

    public CardResponse(Long id, String author, Integer sectionId, String subject, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.author = author;
        this.sectionId = sectionId;
        this.subject = subject;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public static CardResponse from(Card saved) {
        return new CardResponse(saved.getId(), saved.getMemberId(), saved.getSectionId(), saved.getSubject(), saved.getContents(), saved.getCreatedAt());
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean hasSameSubject(String subject) {
        return this.subject.equals(subject);
    }

    public boolean isSameSection(Integer sectionId) {
        return this.sectionId.equals(sectionId);
    }
}
