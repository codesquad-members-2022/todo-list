package todolist.domain;

import lombok.Getter;
import todolist.dto.CardDto;

@Getter
public class Card {

    private Long id;
    private final String section;
    private final String title;
    private final String content;

    public Card(String section, String title, String content) {
        this.section = section;
        this.title = title;
        this.content = content;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CardDto toCardDto() {
        CardDto cardDto = new CardDto(section, title, content);
        cardDto.setId(id);
        return cardDto;
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
