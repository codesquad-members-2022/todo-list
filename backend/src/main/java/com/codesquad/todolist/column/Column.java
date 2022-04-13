package com.codesquad.todolist.column;

import com.codesquad.todolist.card.Card;
import java.util.List;

public class Column {

    private Integer columnId;
    private Integer userId;
    private String columnName;
    private Boolean deleted;

    // relation
    private List<Card> cards;

    public Column(Integer userId, String columnName) {
        this(null, userId, columnName);
    }

    public Column(Integer columnId, Integer userId, String columnName) {
        this.columnId = columnId;
        this.userId = userId;
        this.columnName = columnName;
    }

    public Integer getColumnId() {
        return columnId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getColumnName() {
        return columnName;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Column{" +
            "columnId=" + columnId +
            ", userId=" + userId +
            ", columnName='" + columnName + '\'' +
            ", deleted=" + deleted +
            ", cards=" + cards +
            '}';
    }
}
