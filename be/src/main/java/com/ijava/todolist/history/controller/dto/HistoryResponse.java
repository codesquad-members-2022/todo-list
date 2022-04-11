package com.ijava.todolist.history.controller.dto;

import com.ijava.todolist.history.domain.History;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryResponse {

    private String action;
    private Long cardId;
    private Long oldColumn;
    private Long newColumn;
    private LocalDateTime modifiedDate;

    public HistoryResponse(History history) {
        this.action = history.getAction().name();
        this.cardId = history.getCardId();
        this.oldColumn = history.getColumnsId();
        this.newColumn = history.getColumnsId();
        this.modifiedDate = history.getModifiedDate();
    }

    public HistoryResponse(History history, Long oldColumn) {
        this(history);
        this.oldColumn = oldColumn;
    }
}
