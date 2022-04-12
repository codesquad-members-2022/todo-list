package com.hooria.todo.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityLog {

    private long id;
    private String userId;
    private Action action;
    private String taskTitle;
    private Status fromStatus;
    private Status toStatus;
    private LocalDateTime createdAt;
    private boolean readYn;

    public static ActivityLog of(long id, String userId, Action action, String taskTitle, Status fromStatus,
                                 Status toStatus, LocalDateTime createdAt, boolean readYn) {

        return new ActivityLog(id, userId, action, taskTitle, fromStatus, toStatus, createdAt, readYn);
    }

    public static ActivityLog of(String userId, Action action, String taskTitle, Status fromStatus,
                                 Status toStatus, LocalDateTime createdAt, boolean readYn) {

        return new ActivityLog(0, userId, action, taskTitle, fromStatus, toStatus, createdAt, readYn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityLog that = (ActivityLog) o;
        return id == that.id && readYn == that.readYn && Objects.equals(userId, that.userId) && Objects.equals(action, that.action) && Objects.equals(taskTitle, that.taskTitle) && Objects.equals(fromStatus, that.fromStatus) && Objects.equals(toStatus, that.toStatus) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, action, taskTitle, fromStatus, toStatus, createdAt, readYn);
    }
}
