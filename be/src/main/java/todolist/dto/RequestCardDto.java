package todolist.dto;

import lombok.Getter;
import lombok.Setter;
import todolist.domain.Card;

@Getter
@Setter
public class RequestCardDto {

    private String section;
    private String title;
    private String content;

    public RequestCardDto() {
    }

    public RequestCardDto(String section, String title, String content) {
        this.section = section;
        this.title = title;
        this.content = content;
    }

    public Card toCard() {
        return new Card(section, title, content);
    }
}
