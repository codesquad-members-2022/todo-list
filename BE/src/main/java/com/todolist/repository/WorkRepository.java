package com.todolist.repository;

import com.todolist.domain.Work;
import com.todolist.dto.WorkDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class WorkRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public WorkRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Work> findAllByCategoryId(int categoryId, String userId) {
        Map<String, String> params = new HashMap<>();
        params.put("categoryId", String.valueOf(categoryId));
        params.put("userId", userId);

        return jdbc.query("SELECT id, title, content, delete_flag, created_datetime "
                + "FROM work WHERE delete_flag = 0 AND category_id = :categoryId AND user_id = :userId ORDER BY created_datetime DESC",
            params, workRowMapper());
    }

    public WorkDto save(Work work) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(work);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("INSERT INTO work (category_id, title, content, user_id, delete_flag, created_datetime)"
            + " VALUES (:categoryId, :title, :content, :userId, :deleteFlag, :createdDateTime)", parameters, keyHolder);

        return work.convertToDtoForCreation((keyHolder.getKey()).intValue());
    }

    private RowMapper<Work> workRowMapper() {
        return (rs, rowNum) -> {
            Work work = new Work(
                rs.getObject("id", Integer.class),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("created_datetime").toLocalDateTime()
            );

            return work;
        };
    }
}
