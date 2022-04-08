package com.hooria.todo.dto;

import com.hooria.todo.domain.ActivityLog;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ActivityLogsResponse {

    private final List<ActivityLog> activityLogs;
    private final int totalCount;

    public static ActivityLogsResponse of(List<ActivityLog> activityLogs) {
        return new ActivityLogsResponse(activityLogs, activityLogs.size());
    }
}
