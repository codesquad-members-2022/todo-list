package todo.list.service;

import todo.list.domain.Action;
import todo.list.domain.ActivityLog;
import todo.list.domain.CardStatus;

import java.time.LocalDateTime;

public class ActivityLogDto {
    private Long id;
    private Action action;
    private String title;
    private CardStatus nowStatus;
    private CardStatus beforeStatus;
    private LocalDateTime createDate;

    public ActivityLogDto(ActivityLog activityLog) {
        this.id = activityLog.getId();
        this.action = activityLog.getAction();
        this.title = activityLog.getTitle();
        this.nowStatus = activityLog.getNowStatus();
        this.beforeStatus = activityLog.getBeforeStatus();
        this.createDate = activityLog.getCreateDate();
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }
}
