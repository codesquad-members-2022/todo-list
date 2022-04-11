package com.codesquad.todolist.history;

import java.time.LocalDateTime;

public class History {

    public enum Action {
        CREATE,
        MOVE,
        UPDATE,
        DELETE
    }

    public enum Field {
        TITLE,
        CONTENT,
        AUTHOR,
        COLUMN
    }

    private Integer historyId;
    private Integer cardId;
    private Field field;
    private String oldValue;
    private String newValue;
    private Action action;
    private LocalDateTime createdDateTime;

    public static History ofCreate(Integer cardId) {
        return new History(null, cardId, null, null, null, Action.CREATE, LocalDateTime.now());
    }

    public static History ofUpdate(Integer cardId, Field field, String oldValue, String newValue) {
        return new History(null, cardId, field, oldValue, newValue, Action.UPDATE,
            LocalDateTime.now());
    }

    public History(Integer historyId, Integer cardId, Field field,
        String oldValue, String newValue, Action action, LocalDateTime createdDateTime) {
        this.historyId = historyId;
        this.cardId = cardId;
        this.field = field;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.action = action;
        this.createdDateTime = createdDateTime;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public Field getField() {
        return field;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public Action getAction() {
        return action;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

}
