package todo.list.domain;

import java.time.LocalDateTime;

public class ActivityLog {
    private Long id;
    private Action action;
    private String title;
    private CardStatus nowStatus;
    private CardStatus beforeStatus;
    private LocalDateTime createDateTime;

    public ActivityLog(Action action, String title, CardStatus nowStatus) {
        this(null, action, title, nowStatus, null, LocalDateTime.now());
    }

    public ActivityLog(Long id, Action action, String title, CardStatus nowStatus,
                       CardStatus beforeStatus, LocalDateTime createDateTime) {
        this.id = id;
        this.action = action;
        this.title = title;
        this.nowStatus = nowStatus;
        this.beforeStatus = beforeStatus;
        this.createDateTime = createDateTime;
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
