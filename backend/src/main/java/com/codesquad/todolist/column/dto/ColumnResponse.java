package com.codesquad.todolist.column.dto;

import com.codesquad.todolist.card.dto.CardResponse;
import com.codesquad.todolist.column.Column;
import java.util.List;
import java.util.stream.Collectors;

public class ColumnResponse {

    private final Integer columnId;
    private final String columnName;

    // relation
    private final List<CardResponse> cards;

    public ColumnResponse(Integer columnId, String columnName,
        List<CardResponse> cards) {
        this.columnId = columnId;
        this.columnName = columnName;
        this.cards = cards;
    }

    public static ColumnResponse from(Column column) {
        List<CardResponse> cards = column.getCards() != null
            ? column.getCards().stream()
            .map(CardResponse::from).collect(Collectors.toList())
            : null;

        return new ColumnResponse(
            column.getColumnId(),
            column.getColumnName(),
            cards
        );
    }

    public Integer getColumnId() {
        return columnId;
    }

    public String getColumnName() {
        return columnName;
    }

    public List<CardResponse> getCards() {
        return cards;
    }
}
