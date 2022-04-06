package com.hooria.todo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ActivityLog {

    private long id;
    private String userId;
    private int activityType;
    private int fromStatus;
    private int toStatus;
    private LocalDateTime createdAt;
    private boolean readYn;
}
