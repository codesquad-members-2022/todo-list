package todo.list.service.dto;

import todo.list.domain.Action;
import todo.list.domain.ActivityLog;
import todo.list.domain.CardStatus;

import java.time.LocalDateTime;

public class ActivityLogResponse {
    private Long id;
    private Action action;
    private String title;
    private CardStatus nowStatus;
    private CardStatus beforeStatus;
    private LocalDateTime createDateTime;

    public ActivityLogResponse(ActivityLog activityLog) {
        this.id = activityLog.getId();
        this.action = activityLog.getAction();
        this.title = activityLog.getTitle();
        this.nowStatus = activityLog.getNowStatus();
        this.beforeStatus = activityLog.getBeforeStatus();
        this.createDateTime = activityLog.getCreateDateTime();
    }

    public Long getId() {
        return id;
    }

    public Action getAction() {
        return action;
    }

    public String getTitle() {
        return title;
    }

    public CardStatus getNowStatus() {
        return nowStatus;
    }

    public CardStatus getBeforeStatus() {
        return beforeStatus;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }
}
