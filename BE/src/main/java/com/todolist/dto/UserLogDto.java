package com.todolist.dto;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UserLogDto {

    private String title;
    private String action;
    private String previousCategory;
    private String currentCategory;
    private LocalDateTime updatedDateTime;

    public UserLogDto(String title, String action, String previousCategory,
        String currentCategory, LocalDateTime updatedDateTime) {
        this.title = title;
        this.action = action;
        this.previousCategory = previousCategory;
        this.currentCategory = currentCategory;
        this.updatedDateTime = updatedDateTime;
    }
}
