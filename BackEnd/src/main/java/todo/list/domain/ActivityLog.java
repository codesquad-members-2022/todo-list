package todo.list.domain;

import java.time.LocalDateTime;

public class ActivityLog {
    private Long id;
    private Action action;
    private String title;
    private CardStatus nowStatus;
    private CardStatus beforeStatus;
    private LocalDateTime createDate;

    public ActivityLog(Long id, Action action, String title, CardStatus nowStatus,
                       CardStatus beforeStatus, LocalDateTime createDate) {
        this.id = id;
        this.action = action;
        this.title = title;
        this.nowStatus = nowStatus;
        this.beforeStatus = beforeStatus;
        this.createDate = createDate;
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
