package com.ijava.todolist.card.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "카드 이동 정보")
@Getter
@NoArgsConstructor
public class CardMovedResponse {

    @Schema(description = "카드 아이디")
    private Long cardId;

    @Schema(description = "변경 전 칼럼 아이디")
    private Long oldColumn;

    @Schema(description = "변경 후 칼럼 아이디")
    private Long newColumn;

    public CardMovedResponse(Long cardId, Long oldColumn, Long newColumn) {
        this.cardId = cardId;
        this.oldColumn = oldColumn;
        this.newColumn = newColumn;
    }
}
