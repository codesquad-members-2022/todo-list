package com.hooria.todo.dto;

import com.hooria.todo.domain.ActivityLog;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public class ActivityLogResponse {

    private long id;
    private String userId;
    private String action;
    private String taskTitle;
    private String fromStatus;
    private String toStatus;
    private String createdAt;
    private boolean readYn;

    public static ActivityLogResponse from(ActivityLog activityLog) {
        return new ActivityLogResponse(
                activityLog.getId(),
                activityLog.getUserId(),
                activityLog.getAction().name(),
                activityLog.getTaskTitle(),
                activityLog.getFromStatus().name(),
                activityLog.getToStatus().name(),
                activityLog.getCreatedAt().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                activityLog.isReadYn()
        );
    }
}
