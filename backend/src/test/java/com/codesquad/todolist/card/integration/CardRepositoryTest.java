package com.codesquad.todolist.card.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.column.ColumnRepository;
import com.codesquad.todolist.user.User;
import com.codesquad.todolist.user.UserRepository;
import com.codesquad.todolist.util.GeneratedKeyHolderFactory;
import com.codesquad.todolist.util.KeyHolderFactory;
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
@DisplayName("CardRepository 통합 테스트")
public class CardRepositoryTest {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardRepositoryTest(NamedParameterJdbcTemplate jdbcTemplate,
        KeyHolderFactory keyHolderFactory) {
        this.cardRepository = new CardRepository(jdbcTemplate, keyHolderFactory);
        this.columnRepository = new ColumnRepository(jdbcTemplate, keyHolderFactory);
        this.userRepository = new UserRepository(jdbcTemplate, keyHolderFactory);
    }

    @Test
    @Rollback(false)
    @DisplayName("변경된 카드 정보가 저장소에 저장되고, 카드 정보를 조회해 변경되었는지 확인한다")
    public void cardUpdateTest() {
        // given
        User user = new User("유저 이름");
        User savedUser = userRepository.create(user);

        Column column = new Column(savedUser.getUserId(), "컬럼 이름");
        Column savedColumn = columnRepository.create(column);

        Card card = new Card(savedColumn.getColumnId(), "제목", "내용", "작성자", 1);
        Card savedCard = cardRepository.create(card);

        savedCard.update("변경된 제목", "변경된 내용", "변경된 작성자");
        cardRepository.update(card);

        // when
        Card findCard = cardRepository.findById(savedCard.getCardId());

        // then
        assertThat(findCard.getTitle()).isEqualTo("변경된 제목");
        assertThat(findCard.getContent()).isEqualTo("변경된 내용");
        assertThat(findCard.getAuthor()).isEqualTo("변경된 작성자");
    }
}
