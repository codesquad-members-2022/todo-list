package todolist.dto.card;

import todolist.domain.card.Card;

public class RequestCardDto {

    private String section;
    private String title;
    private String content;

    public RequestCardDto(String section, String title, String content) {
        this.section = section;
        this.title = title;
        this.content = content;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Card toCard() {
        return new Card(section, title, content);
    }

    @Override
    public String toString() {
        return "RequestCardDto{" +
                "section='" + section + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
