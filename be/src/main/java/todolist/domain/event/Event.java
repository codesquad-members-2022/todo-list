package todolist.domain.event;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Event {
    private Long id;
    private String cardTitle;
    private String prevSection;
    private String currentSection;
    private Action action;
    private LocalDateTime createdAt;


    public Event(String cardTitle, String currentSection, Action action) {
        this(cardTitle, "", currentSection, action);
    }

    public Event(String cardTitle, String prevSection, String currentSection, Action action) {
        this(-1L, cardTitle, prevSection, currentSection, action, null);
    }

    public Event(Long id, String cardTitle, String prevSection, String currentSection, Action action, LocalDateTime createdAt) {
        this.id = id;
        this.cardTitle = cardTitle;
        this.prevSection = prevSection;
        this.currentSection = currentSection;
        this.action = action;
        this.createdAt = createdAt;
    }

}
