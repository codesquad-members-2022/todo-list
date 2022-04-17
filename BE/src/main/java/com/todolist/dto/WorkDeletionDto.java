package com.todolist.dto;

import com.todolist.domain.UserLog;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class WorkDeletionDto {

    private String userId;
    private Integer currentCategoryId;
    private String title;

    public UserLog convertToUserLogDomain(String currentCategoryName) {
        return UserLog.builder()
            .userId(userId)
            .title(title)
            .action(Action.DELETION.getAction())
            .currentCategory(currentCategoryName)
            .updatedDateTime(LocalDateTime.now())
            .build();
    }

}
