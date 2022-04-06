package com.ijava.todolist.card.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
public class Card {

    private Long id;
    private final String title;
    private final String content;
    private final Long columnsId;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public Card(Long id, String title, String content, Long columnsId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.columnsId = columnsId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public Card(String title, String content, Long columnsId, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.title = title;
        this.content = content;
        this.columnsId = columnsId;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
