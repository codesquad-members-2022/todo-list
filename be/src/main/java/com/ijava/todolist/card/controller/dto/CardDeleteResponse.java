package com.ijava.todolist.card.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "삭제된 카드 정보")
@Getter
@NoArgsConstructor
public class CardDeleteResponse {

    @Schema(description = "삭제된 카드 아이디")
    private Long cardId;

    public CardDeleteResponse(Long cardId) {
        this.cardId = cardId;
    }
}
