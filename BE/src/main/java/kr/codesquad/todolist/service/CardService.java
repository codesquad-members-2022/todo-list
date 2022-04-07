package kr.codesquad.todolist.service;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.dto.CardDto;
import kr.codesquad.todolist.dto.CardResponse;
import kr.codesquad.todolist.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public CardResponse create(CardDto cardDto) {
        Card saved = cardRepository.save(cardDto.toEntity());
        return CardResponse.from(saved);
    }

    public CardResponse findOne(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return CardResponse.from(card);
    }

    public List<CardResponse> findAll() {
        List<Card> allCard = cardRepository.findAll();
        return allCard.stream()
                .map(CardResponse::from)
                .collect(Collectors.toList());
    }

    public boolean delete(Long id) {
        return cardRepository.delete(id);
    }

}
