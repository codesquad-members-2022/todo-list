package com.todolist.domain;

import com.todolist.dto.WorkLogDto;
import java.time.LocalDateTime;

public class WorkLog {

    private Integer workId;
    private String userId;
    private String title;
    private Action action;
    private String previousStatus;
    private String changedStatus;
    private LocalDateTime updatedDateTime;

    private WorkLog() { }

    public WorkLog(String userId, String title, String action,
        String previousStatus, String changedStatus, LocalDateTime updatedDateTime) {
        this.userId = userId;
        this.title = title;
        this.action = Action.valueOf(action);
        this.previousStatus = previousStatus;
        this.changedStatus = changedStatus;
        this.updatedDateTime = updatedDateTime;
    }

    public WorkLogDto convertToDto() {
        return new WorkLogDto(userId, title, action.getValue(), previousStatus, changedStatus,
            updatedDateTime);
    }

    public enum Action {
        등록("등록"),
        삭제("삭제"),
        변경("변경"),
        이동("이동");

        private String value;

        Action(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
