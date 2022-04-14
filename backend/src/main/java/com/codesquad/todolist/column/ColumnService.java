package com.codesquad.todolist.column;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.dto.ColumnResponse;
import com.codesquad.todolist.exception.BusinessException;
import com.codesquad.todolist.exception.ErrorCode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final CardRepository cardRepository;

    public ColumnService(ColumnRepository columnRepository, CardRepository cardRepository) {
        this.columnRepository = columnRepository;
        this.cardRepository = cardRepository;
    }

    public List<ColumnResponse> findAll() {
        List<Column> columns = columnRepository.findAll();

        List<Card> cards = cardRepository.findAll();

        Map<Integer, List<Card>> allCardMap = cards.stream()
            .collect(Collectors.groupingBy(Card::getColumnId));

        for (Column column : columns) {
            List<Card> columnCards = allCardMap.get(column.getColumnId());

            if (columnCards == null || columnCards.size() == 0) {
                continue;
            }

            Map<Integer, Card> columnCardMap = columnCards.stream()
                .collect(Collectors.toMap(Card::getNextId, Function.identity()));

            Card card = Optional.ofNullable(columnCardMap.remove(null))
                .orElseThrow(() -> new BusinessException(ErrorCode.ILLEGAL_CARD_ORDER));

            List<Card> sortedCards = new ArrayList<>(List.of(card));

            while (columnCardMap.size() > 0) {
                card = columnCardMap.remove(card.getCardId());
                sortedCards.add(card);
            }
            Collections.reverse(sortedCards);
            column.setCards(sortedCards);
        }
        return columns.stream()
            .map(ColumnResponse::from)
            .collect(Collectors.toList());
    }
}
