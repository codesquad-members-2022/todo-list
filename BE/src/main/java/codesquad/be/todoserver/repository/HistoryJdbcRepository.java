package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.History;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class HistoryJdbcRepository implements HistoryRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public HistoryJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public Optional<List<History>> findAllHistory() {
		String sql = "SELECT id, todoId, todoTitle, user, action, fromStatus, toStatus, createdAt FROM HISTORY";

		return Optional.ofNullable(jdbcTemplate.query(sql, historyRowMapper()));
	}

	private RowMapper<History> historyRowMapper() {
		return (rs, rowNum) -> new History(
			rs.getLong("id"),
			rs.getLong("todoId"),
			rs.getString("todoTitle"),
			rs.getString("user"),
			rs.getString("action"),
			rs.getString("fromStatus"),
			rs.getString("toStatus"),
			rs.getString("createdAt")
		);
	}
}
