package com.todolist.domain.dto;

import java.time.LocalDateTime;

import com.todolist.domain.Card;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CardCreateDto {

    // @NotBlank 는 String 에서 사용
    @NotNull(message = "userId 값이 비었습니다.")
    private Integer userId;

    @NotBlank(message = "카드 제목을 입력해주세요.")
    @Size(min = 1, max = 30)
    private String cardTitle;

    @NotBlank(message = "카드 내용을 입력해주세요.")
    @Size(min = 1, max = 500)
    private String cardContent;

    @NotBlank(message = "boardName 값이 비었습니다.")
    private String boardName;

    public CardCreateDto(Integer userId, String cardTitle, String cardContent, String boardName) {
        this.userId = userId;
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
        this.boardName = boardName;
    }

    public Card toCard() {
        return Card.builder()
            .userId(userId)
            .cardTitle(cardTitle)
            .cardContent(cardContent)
            .boardName(boardName)
            .createdTime(LocalDateTime.now())
            .build();
    }
}
