package com.codesquad.todolist.history.unit;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.codesquad.todolist.history.HistoryRepository;
import com.codesquad.todolist.history.HistoryService;
import com.codesquad.todolist.history.ModifiedFieldRepository;
import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.Field;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
import com.codesquad.todolist.history.dto.HistoryResponse;
import com.codesquad.todolist.util.page.Criteria;
import com.codesquad.todolist.util.page.Slice;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("HistoryService 단위 테스트")
public class HistoryServiceTest {

    @Mock
    private HistoryRepository historyRepository;

    @Mock
    private ModifiedFieldRepository modifiedFieldRepository;

    @InjectMocks
    private HistoryService historyService;

    @Test
    @DisplayName("저장된 히스토리 기록 전체와 변경된 필드 기록이 반환된다")
    public void findAllTest() {
        // given
        List<History> histories = List.of(
            new History(4, "유저 이름", "컬럼 이름", "변경된 제목", Action.DELETE, LocalDateTime.now()),
            new History(3, "유저 이름", "컬럼 이름", "변경된 제목", Action.UPDATE, LocalDateTime.now()),
            new History(2, "유저 이름", "컬럼 이름", "제목", Action.MOVE, LocalDateTime.now()),
            new History(1, "유저 이름", "컬럼 이름", "제목", Action.CREATE, LocalDateTime.now())
        );
        List<ModifiedField> modifiedFields = List.of(
            new ModifiedField(1, 3, Field.TITLE, "제목", "변경된 제목"),
            new ModifiedField(2, 3, Field.CONTENT, "내용", "변경된 내용"),
            new ModifiedField(3, 3, Field.AUTHOR, "작성자", "변경된 작성자"),
            new ModifiedField(4, 3, Field.COLUMN, "컬럼", "변경된 컬럼")
        );
        Criteria criteria = new Criteria(1, 10);
        given(historyRepository.findAll(criteria))
            .willReturn(histories);
        given(modifiedFieldRepository.findByHistoryIds(any()))
            .willReturn(modifiedFields);

        // when
        Slice<HistoryResponse> slice = historyService.findAll(criteria);
        List<HistoryResponse> findHistories = slice.getData();

        System.out.println(findHistories);

        // then
        then(findHistories)
            .extracting("historyId")
            .containsExactly(4, 3, 2, 1);

        then(findHistories.get(0).getFields()).isNull();
        then(findHistories.get(1).getFields())
            .extracting("modifiedFieldId").containsExactly(1, 2, 3, 4);
        then(findHistories.get(2).getFields()).isNull();
        then(findHistories.get(3).getFields()).isNull();
    }
}
