package com.todolist.project.web.dto;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.domain.card.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardAddDto {
    private int cardIndex;
    private String title;
    private String contents;
    private String writer;
    private String cardStatus;

    public Card toEntity() {
        CardStatus card_Status = CardStatus.valueOf(this.cardStatus);
        return new Card(cardIndex, title, contents, writer, card_Status);
    }
}
