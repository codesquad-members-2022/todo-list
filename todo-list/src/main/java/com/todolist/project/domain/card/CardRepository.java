package com.todolist.project.domain.card;

import com.todolist.project.domain.CardStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class CardRepository {

	private final static String DELETE_CARD_SQL
		= "DELETE FROM card WHERE id = :id";
	private final static String FIND_CARD_SQL
		= "SELECT id, card_index, title, contents, writer, card_Status, created_date FROM card ORDER BY card_index ASC";
	private final static String FIND_ID_SQL
		= "SELECT id, card_index, title, contents, writer, card_status, created_date FROM card WHERE id = :id";
	private final static String UPDATE_CARD_SQL
		= "UPDATE card SET card_index = :index, title = :title, contents = :contents, card_status = :card_status, created_date = :createTime WHERE id = :id";
	private final static String INSERT_CARD_SQL
		= "INSERT INTO card(card_index, title, contents, writer, created_date, card_status) VALUES (:index,:title,:contents,:writer,:createTime,:card_status)";
	private final static String FIND_CARD_BY_STATUS_SQL
		= "SELECT id, card_index, title, contents, writer, card_status, created_date FROM card WHERE card_status = :cardStatus";
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final RowMapper<Card> rowMapper;

	public CardRepository(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.rowMapper = (rs, rowNum) -> {
			String status = rs.getString("card_Status");
			return new Card(
				rs.getLong("id"),
				rs.getInt("card_index"),
				rs.getString("title"),
				rs.getString("contents"),
				rs.getString("writer"),
				rs.getTimestamp("created_date").toLocalDateTime(),
				Enum.valueOf(CardStatus.class, status)
			);
		};
	}

	public Card findCardById(Long id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return namedParameterJdbcTemplate.queryForObject(FIND_ID_SQL, mapSqlParameterSource,
			rowMapper);
	}

	public List<Card> findCardsByStatus(String cardStatus) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("cardStatus", cardStatus);
		return namedParameterJdbcTemplate.query(FIND_CARD_BY_STATUS_SQL, mapSqlParameterSource,
			rowMapper);
	}

	public List<Card> findAll() {
		return namedParameterJdbcTemplate.query(FIND_CARD_SQL, rowMapper);

	}

	public int add(Card card) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		Map<String, Object> map = new HashMap<>() {{
			put("index", card.getCardIndex());
			put("title", card.getTitle());
			put("contents", card.getContents());
			put("writer", card.getWriter());
			put("createTime", card.createTime());
			put("card_status", card.getCardStatus().name());
		}};
		mapSqlParameterSource.addValues(map);
		return namedParameterJdbcTemplate.update(INSERT_CARD_SQL, mapSqlParameterSource);
	}

	public int remove(Long id) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", id);
		return namedParameterJdbcTemplate.update(DELETE_CARD_SQL, mapSqlParameterSource);
	}

	public int update(Long id, Card card) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		Map<String, Object> map = new HashMap<>() {{
			put("id", id);
			put("index", card.getCardIndex());
			put("title", card.getTitle());
			put("contents", card.getContents());
			put("writer", card.getWriter());
			put("createTime", card.createTime());
			put("card_status", card.getCardStatus().name());
		}};
		mapSqlParameterSource.addValues(map);
		return namedParameterJdbcTemplate.update(UPDATE_CARD_SQL, mapSqlParameterSource);
	}
}
