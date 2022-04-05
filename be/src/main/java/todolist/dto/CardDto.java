package todolist.dto;

import lombok.Getter;
import lombok.Setter;
import todolist.domain.Card;

@Getter
@Setter
public class CardDto {

    private Long id;
    private String section;
    private String title;
    private String content;

    public CardDto(String section, String title, String content) {
        this(-1L, section, title, content);
    }

    public CardDto(Long id, String section, String title, String content) {
        this.id = id;
        this.section = section;
        this.title = title;
        this.content = content;
    }

    public Card toCard() {
        return new Card(section, title, content);
    }
}
