package todolist.dto.event;

import todolist.dto.card.ResponseCardDto;

public class RequestEventDto {
    private String title;
    private String prevSection;
    private String currentSection;


    public RequestEventDto(ResponseCardDto responseCardDto) {
        this("", responseCardDto);
    }

    public RequestEventDto(String prevSection, ResponseCardDto responseCardDto) {
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
