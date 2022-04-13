package com.todolist.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.todolist.domain.UserLog;
import com.todolist.domain.Work;
import java.time.LocalDateTime;

public class WorkMovementDto {

    private static final String MOVEMENT = "이동";

    @JsonProperty private String userId;
    @JsonProperty private Integer workId;
    @JsonProperty private Integer previousCategoryId;
    @JsonProperty private Integer changedCategoryId;
    @JsonProperty private String title;
    @JsonProperty private LocalDateTime updatedDateTime;

    public Work convertToWorkDomain() {
        return new Work(workId, changedCategoryId, updatedDateTime);
    }

    public UserLog convertToUserLogDomain(String previousCategoryName, String changedCategoryName) {
        return new UserLog(userId, title, MOVEMENT, previousCategoryName, changedCategoryName);
    }

    public Integer getPreviousCategoryId() {
        return previousCategoryId;
    }

    public Integer getChangedCategoryId() {
        return changedCategoryId;
    }
}
