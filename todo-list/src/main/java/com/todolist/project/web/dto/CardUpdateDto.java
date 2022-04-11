package com.todolist.project.web.dto;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CardUpdateDto {
    private int cardIndex;
    private String title;
    private String contents;
    private CardStatus cardStatus;

    public Card toEntity() {
        return new Card(cardIndex, title, contents, cardStatus);
    }

    public static CardUpdateDto makeUpdateDto(Card card) {
        return new CardUpdateDto(card.getCardIndex(), card.getTitle(), card.getContents(), card.getCardStatus());
    }
}
