package com.ijava.todolist.history.controller.dto;

import com.ijava.todolist.history.domain.History;
import java.time.LocalDateTime;

import com.ijava.todolist.history.repository.dto.JoinedHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HistoryResponse {

    private String action;
    private Long cardId;
    private String cardTitle;
    private Long oldColumn;
    private String oldColumnName;
    private Long newColumn;
    private String newColumnName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public HistoryResponse(History history) {
        this.action = history.getAction().name();
        this.cardId = history.getCardId();
        this.oldColumn = history.getOldColumnsId();
        this.newColumn = history.getOldColumnsId();
        this.modifiedDate = history.getModifiedDate();
    }

    public HistoryResponse(History history, Long oldColumn) {
        this(history);
        this.oldColumn = oldColumn;
    }

    public static HistoryResponse from(JoinedHistory joinedHistory) {
        HistoryResponse response = new HistoryResponse();
        response.setAction(joinedHistory.getAction().name());
        response.setCardId(joinedHistory.getCardId());
        response.setCardTitle(joinedHistory.getCardTitle());
        response.setOldColumn(joinedHistory.getOldColumnsId());
        response.setOldColumnName(joinedHistory.getOldColumnName());
        response.setNewColumn(joinedHistory.getNewColumnsId());
        response.setNewColumnName(joinedHistory.getNewColumnName());
        response.setCreatedDate(joinedHistory.getCreatedDate());
        response.setModifiedDate(joinedHistory.getModifiedDate());
        return response;
    }
}
