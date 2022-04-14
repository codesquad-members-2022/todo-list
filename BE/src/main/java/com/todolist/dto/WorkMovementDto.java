package com.todolist.dto;

import com.todolist.domain.UserLog;
import com.todolist.domain.Work;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class WorkMovementDto {

    private String userId;
    private Integer workId;
    private Integer previousCategoryId;
    private Integer currentCategoryId;
    private String title;
    private LocalDateTime updatedDateTime;

    public Work convertToWorkDomain() {
        return Work.builder()
            .id(workId)
            .categoryId(currentCategoryId)
            .createdDateTime(updatedDateTime)
            .build();
    }

    public UserLog convertToUserLogDomain(String previousCategoryName, String currentCategoryName) {
        return UserLog.builder()
            .userId(userId)
            .title(title)
            .action(Action.MOVEMENT.getAction())
            .previousCategory(previousCategoryName)
            .currentCategory(currentCategoryName)
            .updatedDateTime(LocalDateTime.now())
            .build();
    }
}
