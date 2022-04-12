package kr.codesquad.todolist.dto.section;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.domain.Section;
import kr.codesquad.todolist.dto.card.CardResponse;

import java.util.List;

public class CardsOfSection {

    private final Section section;
    private final List<CardResponse> cards;

    public CardsOfSection(Section section, List<CardResponse> cards) {
        this.section = section;
        this.cards = cards;
    }

    public Section getSection() {
        return section;
    }

    public List<CardResponse> getCards() {
        return cards;
    }
}
