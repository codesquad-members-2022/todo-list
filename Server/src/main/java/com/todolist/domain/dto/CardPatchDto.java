package com.todolist.domain.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CardPatchDto {

    @NotBlank(message = "카드 제목을 입력해주세요.")
    @Size(min = 1, max = 30)
    private final String cardTitle;

    @NotBlank(message = "카드 내용을 입력해주세요.")
    @Size(min = 1, max = 500)
    private final String cardContent;

    public CardPatchDto(String cardTitle, String cardContent) {
        this.cardTitle = cardTitle;
        this.cardContent = cardContent;
    }
}
