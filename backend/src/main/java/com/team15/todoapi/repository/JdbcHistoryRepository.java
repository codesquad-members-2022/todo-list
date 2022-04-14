package com.team15.todoapi.repository;

import com.team15.todoapi.domain.History;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;


@Repository
public class JdbcHistoryRepository implements HistoryRepository {

	private final NamedParameterJdbcTemplate jdbcTemplate;
	private final DataSourceTransactionManager transactionManager;

	public JdbcHistoryRepository(DataSource dataSource,
		DataSourceTransactionManager transactionManager) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.transactionManager = transactionManager;
	}

	@Override
	public List<History> findAll(Long memberId) {
		Map namedParameters = Collections.singletonMap("memberId", memberId);

		String sql ="SELECT log.id, "
						+ "log.created_at, "
						+ "log.card_id, "
						+ "scode.flag_description old_section, "
						+ "scode2.flag_description current_section, "
						+ "acode.code_description action, "
						+ "card.title "
						+ "FROM card_action_log log	"
						+ "LEFT JOIN card_action_code acode "
						+ "ON log.card_action_code_id = acode.id "
						+ "LEFT JOIN card "
						+ "ON log.card_id = card.id "
						+ "LEFT JOIN card_section_code scode "
						+ "ON log.old_section = scode.id "
						+ "LEFT JOIN card_section_code scode2 "
						+ "ON log.current_section = scode2.id "
						+ "WHERE log.member_id = :memberId "
						+ "ORDER BY log.created_at DESC";

		List<History> histories = jdbcTemplate.query(sql, namedParameters, rowMapper);
		return histories;
	}

	private static RowMapper<History> rowMapper =
		(rs, rowNum) -> History.of(
			rs.getLong("id"),
			rs.getTimestamp("created_at").toLocalDateTime(),
			rs.getLong("card_id"),
			rs.getString("old_section"),
			rs.getString("current_section"),
			rs.getString("action"),
			rs.getString("title"));
}
