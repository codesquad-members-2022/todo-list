package kr.codesquad.todolist.card;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
	public static final long ADDED_NEXT_ORDER = 1L;
	public static final String ERROR_OF_TODO_ID = "error of todoId";
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final JdbcTemplate jdbcTemplate;

	public Card save(Card card) {
		if (Objects.isNull(card.getTodoId())) {
			return insert(card);
		}
		if (update(card) < ADDED_NEXT_ORDER) {
			throw new IllegalArgumentException("error of CardDao - update");
		}
		return card;
	}

	private int update(Card card) {
		Optional<Card> cardInfo = findById(card.getTodoId());
		if (cardInfo.isEmpty()) {
			throw new IllegalArgumentException("cardDao 없데이트 대상이 아닙니다.");
		}
		String sql = "update todo_list_table set subject = :subject, contet = :content where todo_id = :todo_id";
		SqlParameterSource params = new BeanPropertySqlParameterSource(card);
		return namedParameterJdbcTemplate.update(sql, params);
	}

	private Optional<Card> findById(Long todoId) {
		if (todoId < ADDED_NEXT_ORDER) {
			throw new IllegalArgumentException(ERROR_OF_TODO_ID);
		}
		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(CARD_KEY_COLUMN_NAME, todoId);
		String sql = "select * from todo_list_table where todo_id = :todo_id;";
		Card card = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, cardRowMapper());
		return Optional.ofNullable(card);
	}

	/*
		userId 조회 결과가 없으면 - order = 1
		userId 조회 결과 있으면 - order = max(order) + 1
	 */
	private Card insert(Card card) {
		long nextTodoOrder = getMaxTodoOrder(card.getUserId(), card.getStatus().getText());
		card.nextOrder(nextTodoOrder);
		SimpleJdbcInsert simpleJdbcInsert =  new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName(CARD_TABLE_NAME).usingGeneratedKeyColumns(CARD_KEY_COLUMN_NAME);

		Map<String, Object> parameters = getCardMap(card);
		Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		card.setTodoId(key.longValue());
		return card;
	}

	private long getMaxTodoOrder(Long userId, String todoStatus) {
		long maxOrder = ADDED_NEXT_ORDER;
		if (userId < ADDED_NEXT_ORDER) {
			throw new IllegalArgumentException(ERROR_OF_TODO_ID);
		}
		final SqlParameterSource namedParameters = new MapSqlParameterSource()
			.addValue(CARD_TODO_USER_ID, userId)
			.addValue(CARD_TODO_STATUS, todoStatus);
		String sql = "select max(todo_order) from todo_list_table where todo_user_id = :todo_user_id and todo_status = :todo_status and deleted = 0;";
		try {
			maxOrder = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Long.class) + ADDED_NEXT_ORDER;
		} catch (DataAccessException exception) {
			log.error("result of null with userId");
		} finally {
			return maxOrder;  // NPE 발생
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
			Card article = new Card(
				rs.getLong(CARD_KEY_COLUMN_NAME),
				rs.getString(CARD_SUBJECT),
				rs.getString(CARD_CONTENT),
				Card.TodoStatus.from(rs.getString(CARD_TODO_STATUS)),
				rs.getLong(CARD_TODO_ORDER),
				rs.getBoolean(CARD_DELETED),
				rs.getTimestamp(CARD_WRITING_DATE).toLocalDateTime(),
				rs.getLong(CARD_TODO_USER_ID));
			return article;
		};
	}

	public Optional<Card> findByIdAndUserId(Long todoId, Long userId) {
		if (todoId < ADDED_NEXT_ORDER || userId < ADDED_NEXT_ORDER) {
			throw new IllegalArgumentException(ERROR_OF_TODO_ID);
		}
		final SqlParameterSource namedParameters = new MapSqlParameterSource()
			.addValue(CARD_KEY_COLUMN_NAME, todoId)
			.addValue(CARD_TODO_USER_ID, userId);
		String sql = "select * from todo_list_table where todo_id = :todo_id and todo_user_id = :todo_user_id;";
		Card card = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, cardRowMapper());
		return Optional.ofNullable(card);
	}
}
