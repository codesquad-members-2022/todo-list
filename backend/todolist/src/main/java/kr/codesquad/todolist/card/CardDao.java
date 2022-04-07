package kr.codesquad.todolist.card;

import static kr.codesquad.todolist.card.Card.TodoStatus.from;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

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
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final JdbcTemplate jdbcTemplate;

	public Card save(Card card) {
		if (Objects.isNull(card.getTodoId())) {
			return insert(card);
		}
		if (update(card) < 1) {
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
		if (todoId < 1) {
			throw new IllegalArgumentException("error of todoId");
		}
		final SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(CARD_KEY_COLUMN_NAME, todoId);
		String sql = "select * from todo_list_table where todo_id = :todoId;";
		Card card = namedParameterJdbcTemplate.queryForObject(sql, namedParameters, cardRowMapper());
		return Optional.ofNullable(card);
	}

	private Card insert(Card card) {
		SimpleJdbcInsert simpleJdbcInsert =  new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName(CARD_TABLE_NAME).usingGeneratedKeyColumns(CARD_KEY_COLUMN_NAME);

		Map<String, Object> parameters = getCardMap(card);
		Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		card.setTodoId(key.longValue());
		return card;
	}

	private Map<String, Object> getCardMap(Card card) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put(CARD_SUBJECT, card.getSubject());
		parameters.put(CARD_CONTENT, card.getContent());
		parameters.put(CARD_TODO_STATUS, card.getTodoStatus().getNumber());
		parameters.put(CARD_TODO_ORDER, card.getTodoOrder());
		parameters.put(CARD_WRITING_DATE, card.getCreatedAt());
		parameters.put(CARD_TODO_USER_ID, card.getUserId());
		return parameters;
	}


	private RowMapper<Card> cardRowMapper() {
		return (rs, rowNum) -> {
			Card article = new Card(rs.getLong(CARD_KEY_COLUMN_NAME),
				rs.getString(CARD_SUBJECT),
				rs.getString(CARD_CONTENT),
				from(rs.getInt(CARD_TODO_STATUS)),
				rs.getLong(CARD_TODO_ORDER),
				rs.getBoolean("deleted"),
				rs.getObject(CARD_WRITING_DATE, LocalDateTime.class),
				rs.getLong(CARD_TODO_USER_ID));
			return article;
		};
	}
}
