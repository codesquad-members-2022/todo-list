package com.ijava.todolist.card.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "카드 생성 요청")
@Getter
@NoArgsConstructor
public class CardCreateRequest {

    @Schema(description = "칼럼 아이디")
    private Long columnId;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    public CardCreateRequest(Long columnId, String title, String content) {
        this.columnId = columnId;
        this.title = title;
        this.content = content;
    }
}
