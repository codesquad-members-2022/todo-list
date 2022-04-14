package com.ijava.todolist.column.controller.dto;


import com.ijava.todolist.card.service.CardService;
import com.ijava.todolist.column.domain.Column;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "컬럼 정보")
@Getter
@Setter
public class ColumnResponse {

    @Schema(description = "컬럼 아이디")
    private Long columnId;

    @Schema(description = "컬럼 이름")
    private String name;

    @Schema(description = "해당 컬럼에 속하는 카드 개수")
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
