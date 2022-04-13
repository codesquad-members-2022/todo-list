package com.ijava.todolist.card.domain;

import com.ijava.todolist.common.domain.Deleted;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@Builder
public class Card {

    private Long id;
    private String title;
    private String content;
    private Long columnsId;
    private Deleted deleted;
    private final LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Card(Long id, String title, String content, Long columnsId, Deleted deleted, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.columnsId = columnsId;
        this.deleted = deleted;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;

        if (deleted == null) {
            this.deleted = Deleted.N;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void moveColumn(Long columnsId) {
        this.columnsId = columnsId;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void changeModifiedDate() {
        this.modifiedDate = LocalDateTime.now();
    }

    public void delete() {
        deleted = Deleted.Y;
    }

}
