package com.hooria.todo.dto;

import com.hooria.todo.domain.ActivityLog;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class ActionLogResponse {

    private long id;
    private String userId;
    private String activityType;
    private String taskTitle;
    private String fromStatus;
    private String toStatus;
    private String createdAt;
    private boolean readYn;

    public static ActionLogResponse from(ActivityLog activityLog) {
        return new ActionLogResponse(
                activityLog.getId(),
                activityLog.getUserId(),
                activityLog.getActivityType(),
                activityLog.getTaskTitle(),
                activityLog.getFromStatus(),
                activityLog.getToStatus(),
                activityLog.getCreatedAt().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                activityLog.isReadYn()
        );
    }
}
