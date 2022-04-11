package com.hooria.todo.dto;

import com.hooria.todo.domain.ActivityLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class ActivityLogResponse {

    private long id;
    private String userId;
    private String activityType;
    private String taskTitle;
    private String fromStatus;
    private String toStatus;
    private String createdAt;
    private boolean readYn;

    public static ActivityLogResponse from(ActivityLog activityLog) {
        return new ActivityLogResponse(
                activityLog.getId(),
                activityLog.getUserId(),
                activityLog.getActivityType(),
                activityLog.getTaskTitle(),
                activityLog.getFromStatus(),
                activityLog.getToStatus(),
                activityLog.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                activityLog.isReadYn()
        );
    }
}
