package com.todolist.domain;

import com.todolist.dto.WorkLogDto;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class WorkLog {

    private String userId;
    private String title;
    private String action;
    private String previousColumn;
    private String changedColumn;
    private LocalDateTime updatedDateTime;

    private WorkLog() { }

    public WorkLog(String title, String action,
        String previousColumn, String changedColumn, LocalDateTime updatedDateTime) {
        this.title = title;
        this.action = action;
        this.previousColumn = previousColumn;
        this.changedColumn = changedColumn;
        this.updatedDateTime = updatedDateTime;
    }

    public WorkLog(String userId, String title, String action, String previousColumn) {
        this.userId = userId;
        this.title = title;
        this.action = action;
        this.previousColumn = previousColumn;
        this.updatedDateTime = LocalDateTime.now();
    }

    public WorkLogDto convertToDto() {
        return new WorkLogDto(title, action, previousColumn, changedColumn, updatedDateTime);
    }
}
