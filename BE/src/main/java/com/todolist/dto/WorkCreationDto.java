package com.todolist.dto;

import com.todolist.domain.UserLog;
import com.todolist.domain.Work;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class WorkCreationDto {

    private String userId;
    private Integer categoryId;
    private String title;
    private String content;

    public Work convertToWorkDomain() {
        return Work.builder()
            .categoryId(categoryId)
            .title(title)
            .content(content)
            .userId(userId)
            .deleteFlag(false)
            .createdDateTime(LocalDateTime.now().withNano(0))
            .build();
    }

    public UserLog convertToUserLogDomain(String categoryName) {
        return UserLog.builder()
            .userId(userId)
            .title(title)
            .action(Action.CREATION.getAction())
            .currentCategory(categoryName)
            .updatedDateTime(LocalDateTime.now())
            .build();
    }
}
