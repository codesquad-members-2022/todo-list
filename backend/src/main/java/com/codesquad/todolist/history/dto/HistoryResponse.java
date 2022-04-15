package com.codesquad.todolist.history.dto;

import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.History;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryResponse {

    private final Integer historyId;
    private final String userName;
    private final String columnName;
    private final String title;
    private final Action action;
    private final LocalDateTime createDateTime;

    // relation
    private final List<ModifiedFieldResponse> fields;

    public HistoryResponse(Integer historyId, String userName, String columnName, String title,
        Action action, LocalDateTime createDateTime, List<ModifiedFieldResponse> fields) {
        this.historyId = historyId;
        this.userName = userName;
        this.columnName = columnName;
        this.title = title;
        this.action = action;
        this.createDateTime = createDateTime;
        this.fields = fields;
    }

    public static HistoryResponse from(History history) {
        List<ModifiedFieldResponse> fields = history.getFields() != null
            ? history.getFields().stream()
            .map(ModifiedFieldResponse::from)
            .collect(Collectors.toList())
            : null;

        return new HistoryResponse(
            history.getHistoryId(),
            history.getUserName(),
            history.getColumnName(),
            history.getTitle(),
            history.getAction(),
            history.getCreatedDateTime(),
            fields
        );
    }

    public Integer getHistoryId() {
        return historyId;
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

    public Action getAction() {
        return action;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public List<ModifiedFieldResponse> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "HistoryResponse{" +
            "historyId=" + historyId +
            ", userName='" + userName + '\'' +
            ", columnName='" + columnName + '\'' +
            ", title='" + title + '\'' +
            ", action=" + action +
            ", createDateTime=" + createDateTime +
            ", fields=" + fields +
            '}';
    }
}
