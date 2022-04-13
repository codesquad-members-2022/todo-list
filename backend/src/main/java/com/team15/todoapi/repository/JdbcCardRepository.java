package com.team15.todoapi.repository;

import com.team15.todoapi.domain.Card;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Repository
public class JdbcCardRepository implements CardRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final DataSourceTransactionManager transactionManager;

	public JdbcCardRepository(DataSource dataSource, DataSourceTransactionManager transactionManager) {
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
	public int add(Card card) {
		LocalDateTime now = LocalDateTime.now();
		card.insertModifiedAt(card, now);
		BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(card);

		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sqlForCardAdd = "INSERT INTO card "
			+ "(id, title, content, created_at, modified_at, delete_flag, member_id, card_section_code_id) "
			+ "VALUES (:id, :title, :content, :modifiedAt, :modifiedAt, false, :memberId, :section)";

		TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
		TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		int addResult = 0;

		try {
			addResult = jdbcTemplate.update(sqlForCardAdd, namedParameters, keyHolder,
				new String[]{"id"});

			long cardId = keyHolder.getKey().longValue();
			card.insertId(card, cardId);

			//활동 로그 기록
			String sqlForActionLog = "INSERT INTO card_action_log "
				+ "(created_at, card_id, member_id, card_action_code_id) "
				+ "VALUES (now(), :id, :memberId, 1)";
			int logResult = jdbcTemplate.update(sqlForActionLog, namedParameters);
			transactionManager.commit(transactionStatus);
		}catch(DataAccessException e){
			transactionManager.rollback(transactionStatus);
		}

		return addResult;
	}

	private static RowMapper<Card> rowMapper = (rs, rowNum) -> Card.of(rs.getLong("id"),
		rs.getString("title"),
		rs.getString("content"),
		rs.getLong("member_id"),
		rs.getTimestamp("modified_at").toLocalDateTime(),
		rs.getInt("card_section_code_id"));
}
