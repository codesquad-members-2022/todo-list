package com.todolist.domain;

import com.todolist.dto.UserLogDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLog {

    private String userId;
    private String title;
    private String action;
    private String previousCategory;
    private String currentCategory;
    private LocalDateTime updatedDateTime;

    private UserLog() { }

    @Builder
    public UserLog(String userId, String title, String action, String previousCategory,
        String currentCategory, LocalDateTime updatedDateTime) {
        this.userId = userId;
        this.title = title;
        this.action = action;
        this.previousCategory = previousCategory;
        this.currentCategory = currentCategory;
        this.updatedDateTime = updatedDateTime;
    }

    public UserLogDto convertToDto() {
        return new UserLogDto(title, action, previousCategory, currentCategory, updatedDateTime);
    }
}
