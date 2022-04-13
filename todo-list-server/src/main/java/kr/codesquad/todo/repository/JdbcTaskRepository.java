package kr.codesquad.todo.repository;

import kr.codesquad.todo.domain.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
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
        parameters.put("author", task.getAuthor());
        parameters.put("content", task.getContent());
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

        parameters.put("created_at", now);
        parameters.put("status", task.getStatus());
        int key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).intValue();
        task.setIdx(key);
        task.setCreateAt(now);

        return task;
    }

    @Override
    public List<Task> getAll() {
        String sql = "select * from task order by created_at desc";
        List<Task> tasks = jdbcTemplate.query(sql, (rs, rowCount) -> {
            Task task = new Task(rs.getString("title"), rs.getString("content"), rs.getString("author"), rs.getInt("status"));

            task.setCreateAt(rs.getTimestamp("created_at").toLocalDateTime());
            task.setIdx(rs.getInt("idx"));
            return task;
        });
        return tasks;
    }
}
