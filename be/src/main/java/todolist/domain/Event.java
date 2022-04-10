package todolist.domain;

import java.time.LocalDate;

public class Event {
    private Long id;
    private Long cardId;
    private String section;
    private Action action;
    private LocalDate createdAt;

    public Event(Long cardId, String section, Action action) {
        this.cardId = cardId;
        this.section = section;
        this.action = action;
        this.createdAt = LocalDate.now();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
