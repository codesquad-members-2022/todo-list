package com.ijava.todolist.card.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "카드 수정 요청")
@Getter
@NoArgsConstructor
public class CardUpdateRequest {

    @Schema(description = "수정된 제목")
    private String title;

    @Schema(description = "수정된 내용")
    private String content;

    public CardUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
