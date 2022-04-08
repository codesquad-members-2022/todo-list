package todolist.domain;

import lombok.Getter;
import todolist.dto.RequestCardDto;
import todolist.dto.ResponseCardDto;

@Getter
public class Card {

    private Long id;
    private String section;
    private String title;
    private String content;

    public Card(String section, String title, String content) {
        this.section = section;
        this.title = title;
        this.content = content;
    }

    public void update(RequestCardDto requestCardDto) {
        section = requestCardDto.getSection();
        title = requestCardDto.getTitle();
        content = requestCardDto.getContent();
    }

    public void setId(long id) {
        this.id = id;
    }

    public ResponseCardDto toResponseCardDto() {
        ResponseCardDto responseCardDto = new ResponseCardDto(id,section, title, content);
        return responseCardDto;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", section='" + section + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
