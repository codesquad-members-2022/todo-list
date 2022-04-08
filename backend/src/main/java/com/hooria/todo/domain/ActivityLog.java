package com.hooria.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class ActivityLog {

    private long id;
    private String userId;
    private String activityType;
    private String taskTitle;
    private String fromStatus;
    private String toStatus;
    private LocalDateTime createdAt;
    private boolean readYn;

    public static ActivityLog of(String userId, String activityType, String taskTitle, String fromStatus, String toStatus,
                                 LocalDateTime createdAt, boolean readYn) {

        return new ActivityLog(0, userId, activityType, taskTitle, fromStatus, toStatus, createdAt, readYn);
    }
}
