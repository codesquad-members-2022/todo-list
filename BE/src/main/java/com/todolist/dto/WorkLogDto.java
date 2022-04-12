package com.todolist.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class WorkLogDto {

    private String title;
    private String action;
    private String previousColumn;
    private String changedColumn;
    private LocalDateTime updatedDateTime;

    public WorkLogDto(String title, String action, String previousColumn,
        String changedColumn, LocalDateTime updatedDateTime) {
        this.title = title;
        this.action = action;
        this.previousColumn = previousColumn;
        this.changedColumn = changedColumn;
        this.updatedDateTime = updatedDateTime;
    }
}
