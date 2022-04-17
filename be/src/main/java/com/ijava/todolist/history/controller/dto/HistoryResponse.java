package com.ijava.todolist.history.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ijava.todolist.history.domain.History;
import java.time.LocalDateTime;

import com.ijava.todolist.history.repository.dto.JoinedHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "활동 기록 정보")
@Getter
@Setter
@NoArgsConstructor
public class HistoryResponse {

    @Schema(description = "사용자 활동")
    private String action;

    @Schema(description = "카드 아이디")
    private Long cardId;

    @Schema(description = "카드 제목")
    private String cardTitle;

    @Schema(description = "이전 컬럼 아이디")
    private Long oldColumn;

    @Schema(description = "이전 컬럼 이름")
    private String oldColumnName;

    @Schema(description = "이후 컬럼 아이디")
    private Long newColumn;

    @Schema(description = "이후 컬럼 이름")
    private String newColumnName;

    @Schema(description = "최초등록일", example = "2022-04-14T11:22:33", pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Schema(description = "최종수정일", example = "2022-04-14T11:22:33", pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
