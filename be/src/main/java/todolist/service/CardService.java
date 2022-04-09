package todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.domain.Card;
import todolist.dto.RequestCardDto;
import todolist.dto.ResponseCardDto;
import todolist.dto.ResponseCardsDto;
import todolist.repository.TodoRepository;

import java.util.List;

@Service
public class CardService {

    private final TodoRepository<Card> repository;

    @Autowired
    public CardService(TodoRepository<Card> repository) {
        this.repository = repository;
    }

    public ResponseCardsDto getCards() {
        List<Card> cards = repository.findAll();
        return new ResponseCardsDto(cards);
    }

    public ResponseCardDto addCard(RequestCardDto requestCardDto) {
        Card card = repository.save(requestCardDto.toCard());
        return card.toResponseCardDto();
    }

    public void updateCard(Long id, RequestCardDto requestCardDto) {
        Card existingCard = repository.findById(id);
        existingCard.update(requestCardDto);

//        if (card.getSection().equals(requestCardDto.getSection())) {
//            카드 내용 수정 이벤트
//            eventMaker.create(new Event(Action.UPDATE)
//        } else {
//            섹션 이동 이벤트
//            eventMaker.create(new Event(Action.MOVE)
//        }
//        repository.update(existingCard);
    }

    public void deleteCard(Long id) {
        repository.delete(id);
    }
}
