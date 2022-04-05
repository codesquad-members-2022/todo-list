package todo.list.domain;

import java.time.LocalDateTime;

public class ActivityLog {
    private Long id;
    private Action action;
    private String title;
    private CardStatus nowStatus;
    private CardStatus beforeStatus;
    private LocalDateTime createDate;
}
