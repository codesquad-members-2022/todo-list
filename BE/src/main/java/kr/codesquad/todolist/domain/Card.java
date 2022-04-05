package kr.codesquad.todolist.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Card {
    private Long id;
    private final String userName;
    private final Integer column;
    private final String subject;
    private final String contents;
    private final LocalDateTime createTime;
    private final LocalDateTime updateTime;
    private final boolean deleted;

    public Card(Long id, String userName, Integer column, String subject, String contents,
                LocalDateTime createTime, LocalDateTime updateTime, boolean deleted) {
        this.id = id;
        this.userName = userName;
        this.column = column;
        this.subject = subject;
        this.contents = contents;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getColumn() {
        return column;
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
        return getId() == card.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
