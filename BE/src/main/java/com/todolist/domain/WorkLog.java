package com.todolist.domain;

import com.todolist.dto.WorkLogDto;
import java.time.LocalDateTime;

public class WorkLog {

    private Integer workId;
    private String userId;
    private String title;
    private String action;
    private String previousStatus;
    private String changedStatus;
    private LocalDateTime updatedDate;

    private WorkLog() { }

    public WorkLog(Integer workId, String userId, String title, String action, // WorkLog 생성용
        String previousStatus, String changedStatus, LocalDateTime updatedDate) {
        this.workId = workId;
        this.userId = userId;
        this.title = title;
        this.action = action;
        this.previousStatus = previousStatus;
        this.changedStatus = changedStatus;
        this.updatedDate = updatedDate;
    }

    public WorkLog(String userId, String title, String action,
        String previousStatus, String changedStatus, LocalDateTime updatedDate) {
        this.userId = userId;
        this.title = title;
        this.action = action;
        this.previousStatus = previousStatus;
        this.changedStatus = changedStatus;
        this.updatedDate = updatedDate;
    }

    public WorkLogDto convertToDto() {
        return new WorkLogDto(userId, title, action, previousStatus, changedStatus, updatedDate);
    }
}
