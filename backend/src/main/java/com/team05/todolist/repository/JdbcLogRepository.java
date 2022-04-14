package com.team05.todolist.repository;

import com.team05.todolist.domain.Log;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcLogRepository implements LogRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public JdbcLogRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("log")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public int save(Log log) {
        Map<String, Object> params = getSaveParams(log);
        return simpleJdbcInsert.executeAndReturnKey(params).intValue();
    }

    private Map<String, Object> getSaveParams(Log log) {
        Map<String, Object> params = new HashMap<>();
        params.put("event", log.getEventType());
        params.put("log_time", log.getLogTime());
        params.put("title", log.getTitle());
        params.put("prev_section", log.getPrevSection());
        params.put("section", log.getSectionType());
        return params;
    }

    public List<Log> findAll() {
        return jdbcTemplate.query("SELECT id, event, title, log_time, prev_section, section FROM log",
            logRowMapper());
    }

    private RowMapper<Log> logRowMapper() {

        return (rs, rowNum) -> {
//            h2 database
//            Timestamp timestamp = (Timestamp) rs.getObject("log_time");
//            LocalDateTime.ofInstant(timestamp.toInstant(), ZoneOffset.ofHours(0)),
                Log log = new Log(
                rs.getString("event"),
                (LocalDateTime)rs.getObject("log_time"),
                rs.getString("title"),
                rs.getString("prev_section"),
				rs.getString("section")
            );
            log.setId(rs.getInt("id"));
            return log;
        };
    }
}
