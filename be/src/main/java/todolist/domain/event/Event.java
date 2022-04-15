package todolist.domain.event;

import todolist.dto.event.ResponseEventDto;

import java.time.LocalDateTime;

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

    public Long getId() {
        return id;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public String getPrevSection() {
        return prevSection;
    }

    public String getCurrentSection() {
        return currentSection;
    }

    public Action getAction() {
        return action;
    }

    public ResponseEventDto toResponseEventDto() {
        return new ResponseEventDto(id, cardTitle, prevSection, currentSection, action, createdAt);
    }

}
