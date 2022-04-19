package todolist.domain.card;

import java.util.*;

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

    public static Map<String, List<Card>> categorizeCards(List<Card> cardlist) {

        Map<String, List<Card>> result = new HashMap<>() {{
            put(TODO.sectionTitle, new ArrayList<>());
            put(DOING.sectionTitle, new ArrayList<>());
            put(DONE.sectionTitle, new ArrayList<>());
        }};

        for (Card card : cardlist) {
            String section = card.getSection();
            List<Card> responseSection = result.get(findSection(section).sectionTitle);
            responseSection.add(card);
        }
        return result;
    }

    private static Section findSection(String sectionTitle) {
        return Arrays.stream(Section.values())
                .filter(s -> s.getSectionTitle().equals(sectionTitle))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
}
