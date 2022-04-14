package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.History;
import java.sql.PreparedStatement;
import java.util.List;
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
	public List<History> findAllHistory() {
		String sql = "SELECT id, todo_id, todo_title, user, action, from_status, to_status, created_at FROM HISTORY";

		return jdbcTemplate.query(sql, historyRowMapper());
	}

	@Override
	public void saveHistory(History history) {
		String sql =
			"INSERT INTO HISTORY (todo_id, todo_title, user, action, from_status, to_status, created_at)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
			ps.setLong(1, history.getTodoId());
			ps.setString(2, history.getTodoTitle());
			ps.setString(3, history.getUser());
			ps.setString(4, history.getAction());
			ps.setString(5, history.getFromStatus());
			ps.setString(6, history.getToStatus());
			ps.setDate(7, java.sql.Date.valueOf(history.getCreatedAt().toLocalDate()));
			return ps;
		});
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
			rs.getTimestamp("created_at").toLocalDateTime()
		);
	}
}
