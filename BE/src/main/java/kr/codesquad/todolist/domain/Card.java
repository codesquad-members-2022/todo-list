package kr.codesquad.todolist.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Card {

    private final Long id;
    private final String memberId;
    private final Integer sectionId;
    private final String subject;
    private final String contents;
    private final Long orderIndex;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final boolean deleted;

    public Card(Long id, String memberId, Integer sectionId, String subject, String contents, Long orderIndex,
                LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted) {
        this.id = id;
        this.memberId = memberId;
        this.sectionId = sectionId;
        this.subject = subject;
        this.contents = contents;
        this.orderIndex = orderIndex;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleted = deleted;
    }

    public static Card newCard(String memberId, Integer sectionId, String subject, String contents) {
        return new Card(null, memberId, sectionId, subject, contents, null, LocalDateTime.now(), LocalDateTime.now(), false);
    }

    public static Card updateCard(Long id, String memberId, Integer sectionId, String subject, String contents) {
        return new Card(id, memberId, sectionId, subject, contents, null, LocalDateTime.now(), LocalDateTime.now(), false);
    }

    public static Card of(Long id, String memberId, Integer sectionId, String subject, String contents, Long orderIndex,
                          LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted) {
        return new Card(id, memberId, sectionId, subject, contents, orderIndex, createdAt, updatedAt, deleted);
    }

    public static Card withIdAndOrderIndex(Long id, Long orderIndex, Card card) {
        return new Card(id, card.getMemberId(), card.getSectionId(), card.getSubject(), card.getContents(),
                orderIndex, card.getCreatedAt(), card.getUpdatedAt(), card.isDeleted());
    }

    public boolean hasId() {
        return this.id != null;
    }

    public boolean isSameOrderIndex(Long orderIndex) {
        return this.orderIndex.equals(orderIndex);
    }

    public Long getId() {
        return id;
    }

    public String getMemberId() {
        return memberId;
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

    public Long getOrderIndex() {
        return orderIndex;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return getId().equals(card.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", sectionId=" + sectionId +
                ", subject='" + subject + '\'' +
                ", contents='" + contents + '\'' +
                ", orderIndex=" + orderIndex +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deleted=" + deleted +
                '}';
    }
}
