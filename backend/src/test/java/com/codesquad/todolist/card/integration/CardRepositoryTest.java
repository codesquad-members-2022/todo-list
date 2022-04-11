package com.codesquad.todolist.card.integration;

import static org.assertj.core.api.Assertions.*;

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

import com.codesquad.todolist.card.Card;
import com.codesquad.todolist.card.CardRepository;
import com.codesquad.todolist.column.Column;
import com.codesquad.todolist.column.ColumnRepository;
import com.codesquad.todolist.user.User;
import com.codesquad.todolist.user.UserRepository;
import com.codesquad.todolist.util.GeneratedKeyHolderFactory;
import com.codesquad.todolist.util.KeyHolderFactory;

@JdbcTest
@Sql("classpath:/schema.sql")
@Import(GeneratedKeyHolderFactory.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DisplayName("CardRepository 통합 테스트")
public class CardRepositoryTest {

    private final CardRepository cardRepository;
    private final ColumnRepository columnRepository;
    private final UserRepository userRepository;
    private Card card;

    @Autowired
    public CardRepositoryTest(NamedParameterJdbcTemplate jdbcTemplate,
        KeyHolderFactory keyHolderFactory) {
        this.cardRepository = new CardRepository(jdbcTemplate, keyHolderFactory);
        this.columnRepository = new ColumnRepository(jdbcTemplate, keyHolderFactory);
        this.userRepository = new UserRepository(jdbcTemplate, keyHolderFactory);
    }

    @BeforeEach
    public void setUp() {
        User user = userRepository.create(new User("유저 이름"));
        Column column = columnRepository.create(new Column(user.getUserId(), "컬럼 이름"));
        card = cardRepository.create(new Card(column.getColumnId(), "제목", "내용", "작성자", 1));
    }

    @Test
    @DisplayName("요청한 카드 정보가 저장소에 저장되고, 카드 정보를 조회해 저장되었는지 확인한다")
    public void createCardTest() {
        // when
        Optional<Card> optional = cardRepository.findById(card.getCardId());

        // then
        assertThat(optional).hasValueSatisfying(findCard -> {
            assertThat(findCard.getTitle()).isEqualTo("제목");
            assertThat(findCard.getContent()).isEqualTo("내용");
            assertThat(findCard.getAuthor()).isEqualTo("작성자");
        });
    }

    @Test
    @DisplayName("변경된 카드 정보가 저장소에 저장되고, 카드 정보를 조회해 변경되었는지 확인한다")
    public void cardUpdateTest() {
        // given
        card.update("변경된 제목", "변경된 내용", "변경된 작성자");
        cardRepository.update(card);

        // when
        Optional<Card> optional = cardRepository.findById(card.getCardId());

        // then
        assertThat(optional).hasValueSatisfying(findCard -> {
            assertThat(findCard.getTitle()).isEqualTo("변경된 제목");
            assertThat(findCard.getContent()).isEqualTo("변경된 내용");
            assertThat(findCard.getAuthor()).isEqualTo("변경된 작성자");
        });
    }
}
