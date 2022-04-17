package com.ijava.todolist.card.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "카드 이동 요청")
@Getter
@NoArgsConstructor
public class CardMoveRequest {

    @Schema(description = "카드 아이디")
    private Long cardId;

    @Schema(description = "이동할 칼럼 아이디")
    private Long columnId;

    public CardMoveRequest(Long cardId, Long columnId) {
        this.cardId = cardId;
        this.columnId = columnId;
    }
}
