package team03.todoapp.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team03.todoapp.controller.CardLocation;
import team03.todoapp.controller.dto.CardAddFormRequest;
import team03.todoapp.controller.dto.CardMoveFormRequest;
import team03.todoapp.controller.dto.CardUpdateFormRequest;
import team03.todoapp.controller.dto.CardsResponse;
import team03.todoapp.repository.CardRepository;
import team03.todoapp.repository.domain.Card;
import team03.todoapp.repository.domain.History;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final HistoryService historyService;

    public CardService(CardRepository cardRepository, HistoryService historyService) {
        this.cardRepository = cardRepository;
        this.historyService = historyService;
    }

    @Transactional
    public void remove(Long cardId) {
        Card card = findOne(cardId);
        CardLocation pastLocation = card.getCurrentLocation();

        cardRepository.delete(cardId);

        historyService.add(new History("remove", card.getTitle(), Optional.ofNullable(pastLocation), card.getCurrentLocation(),
            LocalDateTime.now()));
    }

    public Long add(CardAddFormRequest cardAddFormRequest) {
        Card card = cardAddFormRequest.toEntity();
        return cardRepository.insert(card);
    }

    @Transactional
    public void move(Long cardId, CardMoveFormRequest cardMoveFormRequest) {
        /**
         * 정상흐름: 데이터 사이에서 사이로 이동하는 경우
         *  0. next를 cardId로 갖고있는 table의 id 조회 - Long beforePrevId에 저장
         *  1. cardId로 이동할 card 컬럼의 next 조회    - Long beforeNextId
         *  2. 이동할 곳의 cardMoveFormRequest의 prevItemId의 next를 cardId로 update
         *  3. cardId의 next를 cardMoveFormRequest의 nextItemId로, location을 cardMoveFormRequest의 destinationLocation으로 update
         *  4. beforePrevId의 next를 beforeNextId로 업데이트
         *
         *  edge - cases:
         *  0. beforePrevId가 null인 경우(card가 첫번째 데이터)
         *  1. cardMoveFormRequest의  nextItemId 혹은 prevItemId 가 null인 경우
         *  2.
         *
         */
        Card card = findOne(cardId);
        Long beforePrevId = cardRepository.findCardIdByNextId(cardId);
        Long beforeNextId = cardRepository.findNextIdByCardId(cardId);
        cardRepository.updateNextIdByCardId(cardId, cardMoveFormRequest.getPrevItemId());
        cardRepository.updateNextIdAndLocationByCardId(cardMoveFormRequest.getNextItemId(), cardMoveFormRequest.getDestinationLocation().name(), cardId);
        cardRepository.updateNextIdByCardId(beforeNextId, beforePrevId);

        historyService.add(new History("move", card.getTitle(), Optional.ofNullable(card.getCurrentLocation()), cardMoveFormRequest.getDestinationLocation(),
            LocalDateTime.now()));
    }

    public void update(Long cardId, CardUpdateFormRequest request) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NoSuchElementException("카드를 찾을 수 없습니다."));

        card.update(request.getTitle(), request.getContent());
        cardRepository.update(card);
    }

    public Card findOne(Long cardId) {
        Card card = cardRepository.findById(cardId)
            .orElseThrow(() -> new NoSuchElementException("카드를 찾을 수 없습니다."));
        return card;
    }

    public CardsResponse findAll() {
        return new CardsResponse(cardRepository.findAll());
    }

}
