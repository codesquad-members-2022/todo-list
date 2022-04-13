package com.todolist.domain;

import com.todolist.dto.UserLogDto;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UserLog {

    private String userId;
    private String title;
    private String action;
    private String previousCategory;
    private String changedCategory;
    private LocalDateTime updatedDateTime;

    private UserLog() { }

    public UserLog(String title, String action,
        String previousCategory, String changedCategory, LocalDateTime updatedDateTime) {
        this.title = title;
        this.action = action;
        this.previousCategory = previousCategory;
        this.changedCategory = changedCategory;
        this.updatedDateTime = updatedDateTime;
    }

    public UserLog(String userId, String title, String action, String previousCategory) {
        this.userId = userId;
        this.title = title;
        this.action = action;
        this.previousCategory = previousCategory;
        this.updatedDateTime = LocalDateTime.now();
    }

    public UserLog(String userId, String title, String action, String previousCategory, String changedCategory) {
        this.userId = userId;
        this.title = title;
        this.action = action;
        this.previousCategory = previousCategory;
        this.changedCategory = changedCategory;
        this.updatedDateTime = LocalDateTime.now();
    }

    public UserLogDto convertToDto() {
        return new UserLogDto(title, action, previousCategory, changedCategory, updatedDateTime);
    }
}
