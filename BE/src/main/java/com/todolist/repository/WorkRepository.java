package com.todolist.repository;

import com.todolist.domain.Work;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class WorkRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public WorkRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Work> findAllWorksByCategoryId(int categoryId, String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("categoryId", String.valueOf(categoryId));
        params.put("userId", userId);

        return jdbc.query("SELECT id, category_id, title, content, delete_flag, created_datetime "
                + "FROM work WHERE delete_flag = 0 AND category_id = :categoryId AND user_id = :userId ORDER BY created_datetime DESC",
            params, workRowMapper());
    }

    private RowMapper<Work> workRowMapper() {
        return (rs, rowNum) -> {
            Work work = new Work(
                rs.getObject("id", Integer.class),
                rs.getObject("category_id", Integer.class),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("created_datetime").toLocalDateTime()
            );

            return work;
        };
    }
}
