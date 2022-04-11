package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.History;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryJdbcRepository implements HistoryRepository{

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public HistoryJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public List<History> findAllHistory() {
		String sql = "SELECT id, todoId, todoTitle, action, fromStatus, toStatus, createdAt FROM HISTORY";
		List<History> histories = jdbcTemplate.query(sql, historyRowMapper());

		return histories;
	}

	private RowMapper<History> historyRowMapper() {
		return (rs, rowNum) -> new History(
			rs.getLong("id"),
			rs.getLong("todoId"),
			rs.getString("todoTitle"),
			rs.getString("action"),
			rs.getString("fromStatus"),
			rs.getString("toStatus"),
			rs.getString("createdAt")
		);
	}
}
