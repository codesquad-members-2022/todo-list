package todo.list.service;

import todo.list.domain.Action;
import todo.list.domain.ActivityLog;
import todo.list.domain.CardStatus;

import java.time.LocalDateTime;

public class ActivityLogDto {
    private int index;
    private Action action;
    private String title;
    private CardStatus nowStatus;
    private CardStatus beforeStatus;
    private LocalDateTime createDate;

    public ActivityLogDto(int index, ActivityLog activityLog) {
        this.index = index;
        this.action = activityLog.getAction();
        this.title = activityLog.getTitle();
        this.nowStatus = activityLog.getNowStatus();
        this.beforeStatus = activityLog.getBeforeStatus();
        this.createDate = activityLog.getCreateDate();
    }

    public int getIndex() {
        return index;
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
