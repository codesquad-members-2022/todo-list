package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoJdbcRepository implements TodoRepository {

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

	@Override
	public Optional<List> findAllTodos() {
		String sql = "SELECT id, title, contents, user, status, created_time, updated_time FROM TODO";
		List<Todo> todos = jdbcTemplate.query(sql, todoRowMapper());
		return Optional.of(todos);
	}

	public RowMapper<Todo> todoRowMapper() {
		return (rs, rowNum) -> {
			Todo todo = new Todo(
				rs.getString("title"),
				rs.getString("contents"),
				rs.getString("user"),
				rs.getString("status"));
			todo.setId(rs.getLong("id"));
			todo.setCreatedAt(rs.getTimestamp("created_time").toLocalDateTime());
			todo.setUpdatedAt(rs.getTimestamp("updated_time").toLocalDateTime());
			return todo;
		};
	}
}
