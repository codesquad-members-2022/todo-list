package com.team05.todolist.repository;

import com.team05.todolist.domain.Log;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
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

    @Override
    public List<Log> findAll() {
        return null;
    }
}
