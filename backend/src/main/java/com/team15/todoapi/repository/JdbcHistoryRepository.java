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

	public JdbcHistoryRepository(DataSource dataSource, DataSourceTransactionManager transactionManager) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.transactionManager = transactionManager;
	}

	@Override
	public List<History> findAll(Long memberId) {
		Map namedParameters = Collections.singletonMap("memberId", memberId);

		String sql = "select log.id, log.created_at, log.card_id, log.member_id, "
			+ "old_section, scode.flag_description old_section_2, current_section, scode2.flag_description current_section_2, "
			+ "acode.code_description, card.title "
			+ "from card_action_log log "
			+ "left join card_action_code acode "
			+ "on log.card_action_code_id = acode.id "
			+ "left join card "
			+ "on log.card_id = card.id "
			+ "left join card_section_code scode "
			+ "on log.old_section = scode.id "
			+ "left join card_section_code scode2 "
			+ "on log.current_section = scode2.id "
			+ "where log.member_id = 2";

		List<History> histories = jdbcTemplate.query(sql, namedParameters, rowMapper);
		return histories;
	}
	private static RowMapper<History> rowMapper = (rs, rowNum) -> History.of(rs.getLong("id"),
		rs.getTimestamp("created_at").toLocalDateTime(),
		rs.getLong("card_id"),
		rs.getLong("member_id"),
		rs.getInt("old_section"),
		rs.getInt("current_section"),
		rs.getString("code_description"),
		rs.getString("title"),
		rs.getString("flag_description"));
}
