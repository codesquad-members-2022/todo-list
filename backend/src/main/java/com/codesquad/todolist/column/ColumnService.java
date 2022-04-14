package com.codesquad.todolist.column;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.dto.ColumnResponse;
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
        // 모든 column 객체와 모든 card 객체를 조회
        List<Column> columns = columnRepository.findAll();
        List<Card> cards = cardRepository.findAll();

        // map 자료구조에 column Id 별로 그룹화된 card 리스트를 저장
        Map<Integer, List<Card>> allCardMap = cards.stream()
            .collect(Collectors.groupingBy(Card::getColumnId));

        for (Column column : columns) {
            // 각 column 을 순회하면서 해당 column 의 card 리스트를 조회
            List<Card> columnCards = allCardMap.get(column.getColumnId());

            if (columnCards == null || columnCards.size() == 0) {
                continue;
            }

            // map 자료구조에 nextId 별로 card 객체를 저장
            Map<Integer, Card> columnCardMap = columnCards.stream()
                .collect(Collectors.toMap(Card::getNextId, Function.identity()));

            // next Id 가 null 인 card (= 컬럼의 맨 마지막 카드) 를 조회
            Card card = Optional.ofNullable(columnCardMap.remove(null))
                .orElseThrow(() -> new IllegalStateException("조건을 만족하는 카드가 없습니다."));

            // 컬럼 내 정렬된 카드가 저장될 리스트를 초기화
            List<Card> sortedCards = new ArrayList<>(List.of(card));

            // map 자료구조에서 이전 카드의 card Id 가 next Id 인 card 를 추출 후 정렬된 리스트에 추가
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
