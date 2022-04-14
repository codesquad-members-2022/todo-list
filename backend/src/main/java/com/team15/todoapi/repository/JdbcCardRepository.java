package com.team15.todoapi.repository;

import com.team15.todoapi.domain.Card;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCardRepository implements CardRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final DataSourceTransactionManager transactionManager;

	public JdbcCardRepository(DataSource dataSource,
		DataSourceTransactionManager transactionManager) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.transactionManager = transactionManager;
	}

	@Override
	public List<Card> findAll(Long memberId) {
		Map namedParameters = Collections.singletonMap("memberId", memberId);

		String sql = "SELECT id, "
			+ "title, "
			+ "content, "
			+ "modified_at, "
			+ "member_id, "
			+ "card_section_code_id "
			+ "from card "
			+ "where card.member_id = :memberId "
			+ "and  delete_flag = false "
			+ "order by modified_at desc";

		List<Card> cards = jdbcTemplate.query(sql, namedParameters, rowMapper);
		return cards;
	}

	@Override
	public Card add(Card card) {
		LocalDateTime now = LocalDateTime.now();
		card.insertModifiedAt(card, now);
		BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(card);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sqlForCardAdd = "INSERT INTO card "
			+ "(id, title, content, created_at, modified_at, delete_flag, member_id, card_section_code_id) "
			+ "VALUES (:id, :title, :content, :modifiedAt, :modifiedAt, false, :memberId, :section)";

		jdbcTemplate.update(sqlForCardAdd, namedParameters, keyHolder, new String[]{"id"});

		long cardId = keyHolder.getKey().longValue();
		card.insertId(card, cardId);

		return card;
	}

	private static RowMapper<Card> rowMapper = (rs, rowNum) -> Card.of(rs.getLong("id"),
		rs.getString("title"),
		rs.getString("content"),
		rs.getLong("member_id"),
		rs.getTimestamp("modified_at").toLocalDateTime(),
		rs.getInt("card_section_code_id"));
}
