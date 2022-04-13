package todo.list.service.dto;

import todo.list.domain.Action;
import todo.list.domain.ActivityLog;
import todo.list.domain.CardStatus;

public class ActivityLogRequest {

    private Action action;
    private String title;
    private CardStatus nowStatus;

    public Action getAction() {
        return action;
    }

    public String getTitle() {
        return title;
    }

    public CardStatus getNowStatus() {
        return nowStatus;
    }

    public ActivityLog toEntity() {
        return new ActivityLog(action, title, nowStatus);
    }
}
