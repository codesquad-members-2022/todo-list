package com.codesquad.todolist.history.domain;

import java.time.LocalDateTime;
import java.util.List;

public class History {

    private Integer historyId;
    private Integer cardId;
    private Action action;
    private LocalDateTime createdDateTime;

    // relation
    private String userName;
    private String columnName;
    private String title;
    private List<ModifiedField> fields;

    public History(Integer cardId, Action action) {
        this.cardId = cardId;
        this.action = action;
        this.createdDateTime = LocalDateTime.now();
    }

    public History(Integer historyId, String userName, String columnName, String title,
        Action action, LocalDateTime createdDateTime) {
        this.historyId = historyId;
        this.userName = userName;
        this.columnName = columnName;
        this.title = title;
        this.action = action;
        this.createdDateTime = createdDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        History history = (History) o;
        return historyId.equals(history.historyId);
    }

    @Override
    public int hashCode() {
        return historyId.hashCode();
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public Action getAction() {
        return action;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public String getUserName() {
        return userName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getTitle() {
        return title;
    }

    public List<ModifiedField> getFields() {
        return fields;
    }

    public void setFields(List<ModifiedField> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "History{" +
            "historyId=" + historyId +
            ", cardId=" + cardId +
            ", action=" + action +
            ", createdDateTime=" + createdDateTime +
            ", userName='" + userName + '\'' +
            ", columnName='" + columnName + '\'' +
            ", title='" + title + '\'' +
            ", fields=" + fields +
            '}';
    }
}
