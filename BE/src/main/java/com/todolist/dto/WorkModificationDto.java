package com.todolist.dto;

import com.todolist.domain.UserLog;
import com.todolist.domain.Work;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class WorkModificationDto {

    private String userId;
    private Integer currentCategoryId;
    private String title;
    private String content;

    public Work convertToWorkDomain(Integer workId) {
        return Work.builder()
            .id(workId)
            .title(title)
            .content(content)
            .build();
    }

    public UserLog convertToUserLogDomain(String title, String currentCategoryName) {
        return UserLog.builder()
            .userId(userId)
            .title(title)
            .action(Action.MODIFICATION.getAction())
            .currentCategory(currentCategoryName)
            .updatedDateTime(LocalDateTime.now())
            .build();
    }
}
