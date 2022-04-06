package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class WorkLogDto {

    private String userId;
    private String title;
    private String action;
    private String previousStatus;
    private String changedStatus;
    private LocalDateTime updatedDate;

    public WorkLogDto(String userId, String title, String action, String previousStatus,
        String changedStatus, LocalDateTime updatedDate) {
        this.userId = userId;
        this.title = title;
        this.action = action;
        this.previousStatus = previousStatus;
        this.changedStatus = changedStatus;
        this.updatedDate = updatedDate;
    }
}
