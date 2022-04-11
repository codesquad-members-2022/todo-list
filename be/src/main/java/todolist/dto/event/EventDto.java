package todolist.dto.event;

import todolist.dto.card.ResponseCardDto;

public class EventDto {
    private String title;
    private String prevSection;
    private String currentSection;


    public EventDto(ResponseCardDto responseCardDto) {
        this("", responseCardDto);
    }

    public EventDto(String prevSection, ResponseCardDto responseCardDto) {
        this.title = responseCardDto.getTitle();
        this.prevSection = prevSection;
        this.currentSection = responseCardDto.getSection();
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
