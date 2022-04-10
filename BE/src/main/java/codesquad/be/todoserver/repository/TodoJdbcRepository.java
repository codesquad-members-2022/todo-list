package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.Todo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TodoJdbcRepository implements TodoRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public TodoJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<Todo> findById(Long id) {
		String sql = "SELECT id, title, contents, user, status, created_time, updated_time FROM TODO WHERE id = ?";
		List<Todo> todos = jdbcTemplate.query(sql, todoRowMapper(), id);

		return todos.stream().findAny();
	}

	public RowMapper<Todo> todoRowMapper() {
		return (rs, rowNum) -> {
			Todo todo = new Todo(
				rs.getString("title"),
				rs.getString("contents"),
				rs.getString("user"),
				rs.getString("status"));
			todo.setId(rs.getLong("id"));
			todo.setCreatedTime(rs.getObject("created_time", LocalDateTime.class));
			todo.setUpdatedTime(rs.getObject("updated_time", LocalDateTime.class));
			return todo;
		};
	}
}
