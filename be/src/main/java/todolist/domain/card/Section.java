package todolist.domain.card;

import todolist.dto.card.ResponseCardDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Section {
    TODO("해야할 일"),
    DOING("하고 있는 일"),
    DONE("완료된 일");


    private String sectionTitle;

    Section(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public static Map<String, List<ResponseCardDto>> categorizeCards(List<Card> cardlist) {

        Map<String, List<ResponseCardDto>> result = new HashMap<>() {{
            put(TODO.sectionTitle, new ArrayList<>());
            put(DOING.sectionTitle, new ArrayList<>());
            put(DONE.sectionTitle, new ArrayList<>());
        }};

        for (Card card : cardlist) {
            String section = card.getSection();
            List<ResponseCardDto> responseSection = result.get(findTitle(section));
            responseSection.add(card.toResponseCardDto());
        }
        return result;
    }

    private static String findTitle(String sectionTitle) {
        return Section.valueOf(sectionTitle).getSectionTitle();
    }
}
