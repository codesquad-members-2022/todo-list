package kr.codesquad.todolist.repository.card;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.domain.Section;

import java.util.List;
import java.util.Optional;

public interface CardRepository {

    Card save(Card card);
    Optional<Card> findById(Long id);
    List<Card> findAll();
    boolean delete(Long id);
    boolean move(Integer targetSectionId, Long targetCardId, Long movingCardId);
    List<Card> findBySectionId(Integer sectionId);
    Optional<Section> findSection(Integer sectionId);
    List<Section> findSections();
}
