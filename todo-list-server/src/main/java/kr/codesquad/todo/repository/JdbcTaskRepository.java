package kr.codesquad.todo.repository;

import kr.codesquad.todo.domain.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcTaskRepository implements TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTaskRepository(DataSource dataSource) throws SQLException {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Task add(Task task) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("task").usingGeneratedKeyColumns("idx");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("title", task.getTitle());
        parameters.put("author_nickname", task.getAuthorNickname());
        parameters.put("content", task.getContent());
        LocalDateTime now = LocalDateTime.now();
        parameters.put("create_at", now);
        parameters.put("status", task.getStatus());
        int key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).intValue();
        task.setIdx(key);
        task.setCreateAt(now);

        return task;
    }
}
