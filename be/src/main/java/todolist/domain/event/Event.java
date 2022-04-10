package todolist.domain.event;

import java.time.LocalDate;

public class Event {
    private Long id;
    private String cardTitle;
    private String prevSection;
    private String currentSection;
    private Action action;
    private LocalDate createdAt;


    public Event(String cardTitle, String currentSection, Action action) {
        this(cardTitle, "", currentSection, action);
    }

    public Event(String cardTitle, String prevSection, String currentSection, Action action) {
        this.cardTitle = cardTitle;
        this.prevSection = prevSection;
        this.currentSection = currentSection;
        this.action = action;
        this.createdAt = LocalDate.now();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
