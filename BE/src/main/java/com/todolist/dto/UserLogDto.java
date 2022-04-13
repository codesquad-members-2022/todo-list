package com.todolist.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UserLogDto {

    private String title;
    private String action;
    private String previousCategory;
    private String changedCategory;
    private LocalDateTime updatedDateTime;

    public UserLogDto(String title, String action, String previousCategory,
        String changedCategory, LocalDateTime updatedDateTime) {
        this.title = title;
        this.action = action;
        this.previousCategory = previousCategory;
        this.changedCategory = changedCategory;
        this.updatedDateTime = updatedDateTime;
    }
}
