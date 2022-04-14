package todolist.dto.event;

import todolist.domain.card.Card;

public class RequestEventDto {
    private String title;
    private String prevSection;
    private String currentSection;


    public RequestEventDto(Card card) {
        this("", card);
    }

    public RequestEventDto(String prevSection, Card card) {
        this.title = card.getTitle();
        this.prevSection = prevSection;
        this.currentSection = card.getSection();
    }

    public String getTitle() {
        return title;
    }

    public String getPrevSection() {
        return prevSection;
    }

    public String getCurrentSection() {
        return currentSection;
    }
}
