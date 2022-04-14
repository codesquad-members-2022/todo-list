package com.example.backend.controller.card.dto;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CardDto implements Serializable {

    private Long id;

    @NotBlank(message = "제목을 반드시 입력해야 합니다.")
    private String title;

    @NotNull(message = "작성자를 반드시 입력해야 합니다.")
    private String writer;
    private Long position;

    @NotBlank(message = "내용을 반드시 입력해야 합니다.")
    private String content;

    private LocalDateTime createdAt;

    @NotNull(message = "카드 타입을 반드시 입력해야 합니다.")
    private CardType cardType;

    @NotNull(message = "회원 번호를 반드시 입력해야 합니다.")
    private Long memberId;

    public CardDto(Long id, String title, String writer, Long position, String content, LocalDateTime createdAt, CardType cardType, Long memberId) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.position = position;
        this.content = content;
        this.createdAt = createdAt;
        this.cardType = cardType;
        this.memberId = memberId;
    }

    public CardDto() {
    }

    public CardDto(Card card) {
        this.id = card.getId();
        this.title = card.getTitle();
        this.writer = card.getWriter();
        this.position = card.getPosition();
        this.content = card.getContent();
        this.createdAt = card.getCreatedAt();
        this.cardType = card.getCardType();
        this.memberId = card.getMemberId();
    }

    public Long getId() {
        return id;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getWriter() {
        return writer;
    }

    public Long getPosition() {
        return position;
    }

    public Long getMemberId() {
        return memberId;
    }


    public Card writeCard() {
        return new Card(title, writer, content, position, memberId, cardType);
    }
}
