package todo.list.service;

import lombok.Getter;
import lombok.Setter;
import todo.list.domain.Action;
import todo.list.domain.ActivityLog;
import todo.list.domain.CardStatus;

import java.time.LocalDateTime;

@Setter
@Getter
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
}
