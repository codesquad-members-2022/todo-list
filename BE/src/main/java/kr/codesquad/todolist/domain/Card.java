package kr.codesquad.todolist.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Card {

    private final Long id;
    private final String userId;
    private final Integer columnId;
    private final String subject;
    private final String contents;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;
    private final boolean deleted;

    private Card(Long id, String userId, Integer columnId, String subject, String contents,
                LocalDateTime createTime, LocalDateTime updateTime, boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.columnId = columnId;
        this.subject = subject;
        this.contents = contents;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public static Card of(String userId, Integer columnId, String subject, String contents) {
        return new Card(null, userId, columnId, subject, contents, LocalDateTime.now(), LocalDateTime.now(), false);
    }

    public static Card of(Long id, String userId, Integer columnId, String subject, String contents,
                          LocalDateTime createTime, LocalDateTime updateTime, boolean deleted) {
        return new Card(id, userId, columnId, subject, contents, createTime, updateTime, deleted);
    }

    public static Card of(Long id, Card card) {
        if(card.getId() != null) {
            return card;
        }
        return new Card(id, card.getUserId(), card.getColumnId(), card.getSubject(), card.getContents(), card.getCreateTime(), card.getUpdateTime(), card.isDeleted());
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public String getSubject() {
        return subject;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
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
}
