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
    private String fromStatus;
    private String toStatus;
    private LocalDateTime createdAt;
    private boolean readYn;

    public static ActivityLog of(String userId, String activityType, String fromStatus, String toStatus,
                                 LocalDateTime createdAt, boolean readYn) {

        return new ActivityLog(0, userId, activityType, fromStatus, toStatus, createdAt, readYn);
    }
}
