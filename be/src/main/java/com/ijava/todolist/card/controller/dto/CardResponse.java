package com.ijava.todolist.card.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ijava.todolist.card.domain.Card;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Schema(description = "카드 정보")
@Getter
@NoArgsConstructor
public class CardResponse {



    @Schema(description = "카드 아이디")
    private Long cardId;

    @Schema(description = "속한 칼럼 아이디")
    private Long columnId;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "최초등록일", example = "2022-04-14T11:22:33")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Schema(description = "최종수정일", example = "2022-04-14T11:22:33")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime modifiedDate;

    public static CardResponse from(Card card) {
        CardResponse response = new CardResponse();
        response.cardId = card.getId();
        response.columnId = card.getColumnsId();
        response.title = card.getTitle();
        response.content = card.getContent();
        response.createdDate = card.getCreatedDate();
        response.modifiedDate = card.getModifiedDate();
        return response;
    }
}


