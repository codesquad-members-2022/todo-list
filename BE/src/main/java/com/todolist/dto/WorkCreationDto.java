package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todolist.domain.UserLog;
import com.todolist.domain.Work;

public class WorkCreationDto {

    private static final String CREATION = "등록";

    @JsonProperty private String userId;
    @JsonProperty private Integer categoryId;
    @JsonProperty private String title;
    @JsonProperty private String content;

    public Work convertToWorkDomain() {
        return new Work(categoryId, title, content, userId);
    }

    public UserLog convertToUserLogDomain(String categoryName) {
        return new UserLog(userId, title, CREATION, categoryName);
    }
}
