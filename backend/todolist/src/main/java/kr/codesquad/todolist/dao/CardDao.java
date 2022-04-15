package kr.codesquad.todolist.dao;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.service.CardStatusNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CardDao {
    public static final String CARD_TABLE_NAME = "todo_list_table";
    public static final String CARD_KEY_COLUMN_NAME = "todo_id";
    public static final String CARD_SUBJECT = "subject";
    public static final String CARD_CONTENT = "content";
    public static final String CARD_TODO_STATUS = "todo_status";
    public static final String CARD_TODO_ORDER = "todo_order";
    public static final String CARD_WRITING_DATE = "writing_date";
    public static final String CARD_TODO_USER_ID = "todo_user_id";
    public static final String CARD_DELETED = "deleted";
    public static final String CARD_NUMBER_OF_STATUS = "number_of_status";

    public static final long ADDED_NEXT_ORDER = 1L;   // insert-getMaxTodoOrder() : default or 다음순서 +1 값

    public static final String ERROR_OF_CARD_ID = "error of cardId";
    public static final String ERROR_OF_CARD_DAO_UPDATE = "error of CardDao - update";
    public static final String ERROR_OR_CARD_DAO_UPDATE_NONE = "cardDao 없데이트 대상이 아닙니다.";
    public static final String ERROR_OF_CARD_DAO_BY_FK_USER_ID = "result of null with userId";
    public static final String ERROR_OF_CARD_DAO_USER_ID = "cardDao - userId";
    public static final int CARD_RESULT_OF_UPDATE_LENGTH = 1;
    public static final int CARD_MIN_ID = CARD_RESULT_OF_UPDATE_LENGTH;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public Card save(Card card) {
        if (Objects.isNull(card.getCardId())) {
            return insert(card);
        }
        if (update(card) < CARD_RESULT_OF_UPDATE_LENGTH) {
            throw new IllegalArgumentException(ERROR_OF_CARD_DAO_UPDATE);
        }
        return card;
    }

    private int update(Card card) {
        Optional<Card> cardInfo = findById(card.getCardId());
        if (cardInfo.isEmpty()) {
            throw new IllegalArgumentException(ERROR_OR_CARD_DAO_UPDATE_NONE);
        }
        String sql = "update todo_list_table set subject = :subject, content = :content where todo_id = :todo_id";
        final SqlParameterSource params = new MapSqlParameterSource()
            .addValue(CARD_SUBJECT, card.getSubject())
            .addValue(CARD_CONTENT, card.getContent())
            .addValue(CARD_KEY_COLUMN_NAME, card.getCardId());
        return namedParameterJdbcTemplate.update(sql, params);
    }

    public Optional<Card> findById(Long todoId) {
        if (todoId < ADDED_NEXT_ORDER) {
            throw new IllegalArgumentException(ERROR_OF_CARD_ID);
        }
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(CARD_KEY_COLUMN_NAME, todoId);
        String sql = "select * from todo_list_table where deleted = 0 and todo_id = :todo_id;";
        Card card = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, cardRowMapper());
        return Optional.ofNullable(card);
    }

    /**
     * userId 조회 결과가 없으면 - order = 1
     * userId 조회 결과 있으면 - order = max(order) + 1
     * @param card
     * @return Card
     */
    private Card insert(Card card) {
        long nextTodoOrder = getMaxTodoOrder(card.getUserId(), card.getStatus().getText());
        card.setOrder(nextTodoOrder);
        Map<String, Object> parameters = getCardMap(card);

        Number key = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName(CARD_TABLE_NAME)
            .usingGeneratedKeyColumns(CARD_KEY_COLUMN_NAME)
            .usingColumns(parameters.keySet().toArray(String[]::new))
            .executeAndReturnKey(new MapSqlParameterSource(parameters));

        card.setCardId(key.longValue());
        return card;
    }

    private long getMaxTodoOrder(Long userId, String todoStatus) {
        long maxOrder = ADDED_NEXT_ORDER;
        if (userId < CARD_MIN_ID) {
            throw new IllegalArgumentException(ERROR_OF_CARD_ID);
        }
        final SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue(CARD_TODO_USER_ID, userId)
            .addValue(CARD_TODO_STATUS, todoStatus);
        String sql = "select max(todo_order) from todo_list_table where todo_user_id = :todo_user_id and todo_status = :todo_status and deleted = 0;";
        try {
            maxOrder = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Long.class) + ADDED_NEXT_ORDER;
        } catch (DataAccessException exception) {
            log.error(ERROR_OF_CARD_DAO_BY_FK_USER_ID);
        } finally {
            return maxOrder;
        }
    }

    private Map<String, Object> getCardMap(Card card) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(CARD_SUBJECT, card.getSubject());
        parameters.put(CARD_CONTENT, card.getContent());
        parameters.put(CARD_TODO_STATUS, card.getStatus().getText());
        parameters.put(CARD_TODO_ORDER, card.getOrder());
        parameters.put(CARD_WRITING_DATE, card.getCreatedAt());
        parameters.put(CARD_TODO_USER_ID, card.getUserId());
        return parameters;
    }

    private RowMapper<Card> cardRowMapper() {
        return (rs, rowNum) -> {
            Card card = new Card(
                rs.getLong(CARD_KEY_COLUMN_NAME),
                rs.getString(CARD_SUBJECT),
                rs.getString(CARD_CONTENT),
                Card.TodoStatus.from(rs.getString(CARD_TODO_STATUS)),
                rs.getLong(CARD_TODO_ORDER),
                rs.getBoolean(CARD_DELETED),
                rs.getTimestamp(CARD_WRITING_DATE).toLocalDateTime(),
                rs.getLong(CARD_TODO_USER_ID));
            return card;
        };
    }

    public List<Card> findByUserIdAndTodoStatus(Long userId, Card.TodoStatus todo) {
        if (userId < CARD_MIN_ID) {
            throw new IllegalArgumentException(ERROR_OF_CARD_DAO_USER_ID);
        }
        final SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue(CARD_TODO_USER_ID, userId)
            .addValue(CARD_TODO_STATUS, todo.getText());
		/*
			TODO (honux)
			 order by - 인덱싱
			 where filter - full scan 후 정렬 : O(n)
		 */
        String sql = "select * from todo_list_table where deleted = 0 and todo_user_id = :todo_user_id and todo_status = :todo_status order by todo_order desc;";
        return namedParameterJdbcTemplate.query(sql, namedParameters, cardRowMapper());
    }

    public List<CardStatusNumber> findGroupByTodoStatus(Long userId) {
        if (userId < CARD_MIN_ID) {
            throw new IllegalArgumentException(ERROR_OF_CARD_DAO_USER_ID);
        }
        final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(CARD_TODO_USER_ID, userId);
        String sql = "select count(todo_status) as number_of_status, todo_status from todo_list_table where todo_user_id = :todo_user_id and deleted = 0 group by todo_status;";
        return namedParameterJdbcTemplate.query(sql, namedParameters, cardStatusNumberRowMapper());
    }

    private RowMapper<CardStatusNumber> cardStatusNumberRowMapper() {
        return (rs, rowNum) -> {
            CardStatusNumber cardStatusNumber = new CardStatusNumber(
                Card.TodoStatus.from(rs.getString(CARD_TODO_STATUS)),
                rs.getLong(CARD_NUMBER_OF_STATUS));
            return cardStatusNumber;
        };
    }

    public void delete(Long cardId) {
        String sql = "update todo_list_table set deleted = 1 where todo_id = :todo_id";
        final SqlParameterSource params = new MapSqlParameterSource().addValue(CARD_KEY_COLUMN_NAME, cardId);
        this.namedParameterJdbcTemplate.update(sql, params);
    }

    public void updatePosition(Card card, Card.TodoStatus toStatus, Long toOrder) {
        shiftAffectedCards(card, toStatus, toOrder);

        jdbcTemplate.update("UPDATE todo_list_table SET todo_status = ?, todo_order = ? WHERE todo_id = ?;",
            toStatus.getText(), toOrder, card.getCardId());
    }

    private void shiftAffectedCards(Card card, Card.TodoStatus toStatus, Long toOrder) {
        String shiftingOperation = " + 1";
        String lowerBoundCondition = " AND todo_order > ";
        String upperBoundCondition = " AND todo_order < ";

        if (!toStatus.equals(card.getStatus())) {
            lowerBoundCondition += toOrder - CARD_MIN_ID;
            upperBoundCondition = "";
        }

        if (toOrder > card.getOrder()) {
            shiftingOperation = " - 1";
            lowerBoundCondition += card.getOrder();
            upperBoundCondition += toOrder + CARD_MIN_ID;
        }

        if (toOrder < card.getOrder()) {
            lowerBoundCondition += toOrder - CARD_MIN_ID;
            upperBoundCondition += card.getOrder();
        }

        String sql = "UPDATE todo_list_table SET todo_order = todo_order" + shiftingOperation +
            " WHERE todo_user_id = ? AND todo_status = ?" + lowerBoundCondition + upperBoundCondition + ";";
        jdbcTemplate.update(sql, card.getUserId(), toStatus.getText());
    }
}
