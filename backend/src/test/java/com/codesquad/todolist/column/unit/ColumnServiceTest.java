package com.codesquad.todolist.column.unit;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.column.ColumnRepository;
import com.codesquad.todolist.column.ColumnService;
import com.codesquad.todolist.column.dto.ColumnResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ColumnServiceTest {

    @Mock
    ColumnRepository columnRepository;

    @Mock
    CardRepository cardRepository;

    @InjectMocks
    ColumnService columnService;

    @Test
    @DisplayName("컬럼 내에 카드가 포함된 목록을 반환 받는다")
    public void findAllTest() {
        // given
        List<Column> columns = List.of(
            new Column(1, 1, "컬럼 1"),
            new Column(2, 1, "컬럼 2"),
            new Column(3, 1, "컬럼 3")
        );
        List<Card> cards = List.of(
            new Card(1, 1, "카드 1", "내용", "작성자", null, LocalDateTime.now()),
            new Card(2, 1, "카드 2", "내용", "작성자", 1, LocalDateTime.now()),
            new Card(3, 1, "카드 3", "내용", "작성자", 2, LocalDateTime.now()),
            new Card(4, 2, "카드 4", "내용", "작성자", null, LocalDateTime.now()),
            new Card(5, 2, "카드 5", "내용", "작성자", 4, LocalDateTime.now()),
            new Card(6, 2, "카드 6", "내용", "작성자", 5, LocalDateTime.now())
        );
        given(columnRepository.findAll()).willReturn(columns);
        given(cardRepository.findAll()).willReturn(cards);

        // when
        List<ColumnResponse> findColumns = columnService.findAll();

        // then
        ColumnResponse firstColumn = findColumns.get(0);
        ColumnResponse secondColumn = findColumns.get(1);
        ColumnResponse thirdColumn = findColumns.get(2);

        then(findColumns)
            .extracting("columnName")
            .containsExactly("컬럼 1", "컬럼 2", "컬럼 3");

        then(firstColumn.getCards())
            .extracting("title")
            .containsExactly("카드 3", "카드 2", "카드 1");

        then(secondColumn.getCards())
            .extracting("title")
            .containsExactly("카드 6", "카드 5", "카드 4");

        then(thirdColumn.getCards()).isNull();
    }
}
