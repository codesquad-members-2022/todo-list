package com.ijava.todolist.column.controller.dto;


import com.ijava.todolist.card.service.CardService;
import com.ijava.todolist.column.domain.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnResponse {

    private Long columnId;
    private String name;
    private int cardCount;

    public static ColumnResponse from(Column column, CardService cardService) {
        Long id = column.getId();
        int cardCount = cardService.getCountOfCardsOnColumns(id);

        ColumnResponse columnResponse = new ColumnResponse();
        columnResponse.setColumnId(id);
        columnResponse.setName(column.getName());
        columnResponse.setCardCount(cardCount);
        return columnResponse;
    }
}
