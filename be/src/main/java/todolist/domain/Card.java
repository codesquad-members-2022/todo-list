package todolist.domain;

import todolist.dto.CardDto;

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
        return new CardDto(section, title, content);
    }

}
