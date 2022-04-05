package todo.list.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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

}
