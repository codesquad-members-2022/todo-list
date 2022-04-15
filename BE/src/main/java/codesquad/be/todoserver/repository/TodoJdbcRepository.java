package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.Todo;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TodoJdbcRepository implements TodoRepository {

	private final JdbcTemplate jdbcTemplate;

	public TodoJdbcRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Optional<Todo> findById(Long id) {
		String sql = "SELECT id, title, contents, user, status, created_at, updated_at FROM TODO WHERE id = ? AND DELETED = 0";
		List<Todo> todos = jdbcTemplate.query(sql, todoRowMapper(), id);

		return todos.stream().findAny();
	}

	@Override
	public List<Todo> findAll() {
		String sql = "SELECT id, title, contents, user, status, created_at, updated_at FROM TODO WHERE DELETED = 0";
		List<Todo> todos = jdbcTemplate.query(sql, todoRowMapper());
		return todos;
	}

	@Override
	public Todo save(Todo todo) {
		String sql = "INSERT INTO TODO (TITLE, CONTENTS, USER, STATUS, CREATED_AT, UPDATED_AT) VALUES (?, ?, ?, ?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getContents());
			ps.setString(3, todo.getUser());
			ps.setString(4, todo.getStatus());
			ps.setDate(5, java.sql.Date.valueOf(todo.getCreatedAt().toLocalDate()));
			ps.setDate(6, java.sql.Date.valueOf(todo.getUpdatedAt().toLocalDate()));
			return ps;
		}, keyHolder);
		todo.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

		return todo;
	}

	@Override
	public boolean deleteById(Long id) {
		String sql = "UPDATE TODO SET deleted = 1 WHERE id = ?";
		return jdbcTemplate.update(sql, id) == 1;
	}

	public RowMapper<Todo> todoRowMapper() {
		return (rs, rowNum) -> {
			Todo todo = new Todo(
				rs.getString("title"),
				rs.getString("contents"),
				rs.getString("user"),
				rs.getString("status"));
			todo.setId(rs.getLong("id"));
			todo.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
			todo.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
			return todo;
		};
	}
}
