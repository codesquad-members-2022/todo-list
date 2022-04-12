package com.codesquad.todolist.history.integration;

import static org.assertj.core.api.BDDAssertions.then;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.column.ColumnRepository;
import com.codesquad.todolist.history.HistoryRepository;
import com.codesquad.todolist.history.ModifiedFieldRepository;
import com.codesquad.todolist.history.domain.Action;
import com.codesquad.todolist.history.domain.Field;
import com.codesquad.todolist.history.domain.History;
import com.codesquad.todolist.history.domain.ModifiedField;
import com.codesquad.todolist.user.User;
import com.codesquad.todolist.user.UserRepository;
import com.codesquad.todolist.util.GeneratedKeyHolderFactory;
import com.codesquad.todolist.util.KeyHolderFactory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql("classpath:/schema.sql")
@Import(GeneratedKeyHolderFactory.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@DisplayName("ModifiedFieldRepository 통합 테스트")
public class ModifiedFieldRepositoryTest {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final ModifiedFieldRepository modifiedFieldRepository;

    private User user;
    private Column column;
    private Card card;
    private History history;

    @Autowired
    public ModifiedFieldRepositoryTest(NamedParameterJdbcTemplate jdbcTemplate,
        KeyHolderFactory keyHolderFactory) {
        this.cardRepository = new CardRepository(jdbcTemplate, keyHolderFactory);
        this.columnRepository = new ColumnRepository(jdbcTemplate, keyHolderFactory);
        this.userRepository = new UserRepository(jdbcTemplate, keyHolderFactory);
        this.historyRepository = new HistoryRepository(jdbcTemplate, keyHolderFactory);
        this.modifiedFieldRepository = new ModifiedFieldRepository(jdbcTemplate);
    }

    @BeforeEach
    public void setUp() {
        user = userRepository.create(new User("유저 이름"));
        column = columnRepository.create(new Column(user.getUserId(), "컬럼 이름"));
        card = cardRepository.create(new Card(column.getColumnId(), "제목", "내용", "작성자", 1));
        history = historyRepository.create(new History(card.getCardId(), Action.CREATE));
    }

    @Test
    @DisplayName("요청된 변경 필드 정보가 저장소에 저장되고, 특정 히스토리 정보로부터 변경 필드 정보를 조회해 저장되었는지 확인한다")
    public void createModifiedFieldTest() {
        // given
        Column column_2 = columnRepository.create(new Column(user.getUserId(), "이동한 컬럼 이름"));

        List<ModifiedField> modifiedFields = List.of(
            new ModifiedField(null, history.getHistoryId(), Field.TITLE, "제목", "변경된 제목"),
            new ModifiedField(null, history.getHistoryId(), Field.CONTENT, "내용", "변경된 내용"),
            new ModifiedField(null, history.getHistoryId(), Field.AUTHOR, "작성자", "변경된 작성자"),
            new ModifiedField(null, history.getHistoryId(), Field.COLUMN,
                column.getColumnId().toString(), column_2.getColumnId().toString())
        );
        modifiedFieldRepository.createAll(modifiedFields);

        // when
        List<ModifiedField> findModifiedFields = modifiedFieldRepository.findByHistoryIds(
            List.of(history.getHistoryId()));

        // then
        ModifiedField first = findModifiedFields.get(0);
        ModifiedField second = findModifiedFields.get(1);
        ModifiedField third = findModifiedFields.get(2);
        ModifiedField fourth = findModifiedFields.get(3);

        then(first.getField()).isEqualTo(Field.TITLE);
        then(first.getOldValue()).isEqualTo("제목");
        then(first.getNewValue()).isEqualTo("변경된 제목");

        then(second.getField()).isEqualTo(Field.CONTENT);
        then(second.getOldValue()).isEqualTo("내용");
        then(second.getNewValue()).isEqualTo("변경된 내용");

        then(third.getField()).isEqualTo(Field.AUTHOR);
        then(third.getOldValue()).isEqualTo("작성자");
        then(third.getNewValue()).isEqualTo("변경된 작성자");

        then(fourth.getField()).isEqualTo(Field.COLUMN);
        then(fourth.getOldValue()).isEqualTo("컬럼 이름");
        then(fourth.getNewValue()).isEqualTo("이동한 컬럼 이름");
    }

}
