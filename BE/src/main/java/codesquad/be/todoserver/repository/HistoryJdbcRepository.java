package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HistoryJdbcRepository implements HistoryRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public HistoryJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public List<History> findAllHistory() {
		String sql = "SELECT id, todo_id, todo_title, user, action, from_status, to_status, created_at FROM HISTORY";

		return jdbcTemplate.query(sql, historyRowMapper());
	}

	private RowMapper<History> historyRowMapper() {
		return (rs, rowNum) -> new History(
			rs.getLong("id"),
			rs.getLong("todo_id"),
			rs.getString("todo_title"),
			rs.getString("user"),
			rs.getString("action"),
			rs.getString("from_status"),
			rs.getString("to_status"),
			rs.getString("created_at")
		);
	}
}
