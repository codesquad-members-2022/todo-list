package todolist.dto.event;

import todolist.domain.event.Action;

import java.time.LocalDateTime;

public class ResponseEventDto {

    private Long id;
    private String cardTitle;
    private String prevSection;
    private String currentSection;
    private Action action;
    private LocalDateTime createdAt;

    public ResponseEventDto(Long id, String cardTitle, String prevSection, String currentSection, Action action, LocalDateTime createdAt) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
