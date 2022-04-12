package com.todolist.domain;

import com.todolist.dto.UserLogDto;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UserLog {

    private String userId;
    private String title;
    private String action;
    private String previousColumn;
    private String changedColumn;
    private LocalDateTime updatedDateTime;

    private UserLog() { }

    public UserLog(String title, String action,
        String previousColumn, String changedColumn, LocalDateTime updatedDateTime) {
        this.title = title;
        this.action = action;
        this.previousColumn = previousColumn;
        this.changedColumn = changedColumn;
        this.updatedDateTime = updatedDateTime;
    }

    public UserLog(String userId, String title, String action, String previousColumn) {
        this.userId = userId;
        this.title = title;
        this.action = action;
        this.previousColumn = previousColumn;
        this.updatedDateTime = LocalDateTime.now();
    }

    public UserLogDto convertToDto() {
        return new UserLogDto(title, action, previousColumn, changedColumn, updatedDateTime);
    }
}
