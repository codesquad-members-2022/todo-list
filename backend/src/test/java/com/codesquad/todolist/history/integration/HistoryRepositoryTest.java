package com.codesquad.todolist.history.integration;

import static org.assertj.core.api.BDDAssertions.then;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.column.ColumnRepository;
import com.codesquad.todolist.history.HistoryRepository;
import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.user.User;
import com.codesquad.todolist.user.UserRepository;
import com.codesquad.todolist.util.GeneratedKeyHolderFactory;
import com.codesquad.todolist.util.KeyHolderFactory;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql("classpath:/schema.sql")
@Import(GeneratedKeyHolderFactory.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DisplayName("HistoryRepository 통합 테스트")
public class HistoryRepositoryTest {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private Card card;

    @Autowired
    public HistoryRepositoryTest(NamedParameterJdbcTemplate jdbcTemplate,
        KeyHolderFactory keyHolderFactory) {
        this.cardRepository = new CardRepository(jdbcTemplate, keyHolderFactory);
        this.columnRepository = new ColumnRepository(jdbcTemplate, keyHolderFactory);
        this.userRepository = new UserRepository(jdbcTemplate, keyHolderFactory);
        this.historyRepository = new HistoryRepository(jdbcTemplate, keyHolderFactory);
    }


    @BeforeEach
    public void setUp() {
        User user = userRepository.create(new User("유저 이름"));
        Column column = columnRepository.create(new Column(user.getUserId(), "컬럼 이름"));
        card = cardRepository.create(new Card(column.getColumnId(), "제목", "내용", "작성자", 1));
    }

    @Test
    @DisplayName("요청된 히스토리 정보가 저장소에 저장되고, 히스토리 정보를 조회해 저장되었는지 확인한다")
    public void createHistoryTest() {
        // given
        History history = historyRepository.create(new History(card.getCardId(), Action.CREATE));

        // when
        Optional<History> optional = historyRepository.findById(history.getHistoryId());

        // then
        then(optional).hasValueSatisfying(findHistory -> {
            then(findHistory.getUserName()).isEqualTo("유저 이름");
            then(findHistory.getColumnName()).isEqualTo("컬럼 이름");
            then(findHistory.getTitle()).isEqualTo("제목");
            then(findHistory.getFields()).isNull();
            then(findHistory.getAction()).isEqualTo(Action.CREATE);
        });
    }

    @Test
    @DisplayName("저장소에 저장된 히스토리 정보가 반환된다")
    public void findAllTest() {
        // given
        History history_1 = historyRepository.create(new History(card.getCardId(), Action.CREATE));
        History history_2 = historyRepository.create(new History(card.getCardId(), Action.MOVE));
        History history_3 = historyRepository.create(new History(card.getCardId(), Action.UPDATE));
        History history_4 = historyRepository.create(new History(card.getCardId(), Action.DELETE));

        List<History> histories = List.of(history_1, history_2, history_3, history_4);

        // when
        List<History> findHistories = historyRepository.findAll();

        // then
        then(histories).containsExactlyElementsOf(findHistories);
    }


}
