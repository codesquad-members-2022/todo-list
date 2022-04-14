package com.todolist.repository;

import com.todolist.domain.Work;
import com.todolist.dto.ModifiedWorkDto;
import com.todolist.dto.WorkDto;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
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

    public List<Work> findAllByCategoryId(Integer categoryId) {
        return jdbc.query("SELECT id, title, content, created_datetime "
                + "FROM work WHERE delete_flag = 0 AND category_id = :categoryId ORDER BY created_datetime DESC",
            Collections.singletonMap("categoryId", categoryId), workRowMapper());
    }

    public String findTitleById(Integer workId) {
        return jdbc.queryForObject("SELECT title FROM work WHERE id = :workId", Collections.singletonMap("workId", workId), String.class);
    }

    public WorkDto save(Work work) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(work);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("INSERT INTO work (category_id, title, content, delete_flag, created_datetime)"
            + " VALUES (:categoryId, :title, :content, :deleteFlag, :createdDateTime)", parameters, keyHolder);

        return work.convertToDtoForCreation((keyHolder.getKey()).intValue());
    }

    public void updateCategory(Work work) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(work);
        jdbc.update("UPDATE work SET category_id = :categoryId, created_datetime = :createdDateTime WHERE id = :id", parameters);
    }

    public void delete(Integer workId) {
        jdbc.update("DELETE FROM work WHERE id = :workId", Collections.singletonMap("workId", workId));
    }

    public ModifiedWorkDto updateCard(Work work) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(work);
        jdbc.update("UPDATE work SET title = :title, content = :content WHERE id = :id", parameters);

        return work.convertToDtoForModification();
    }

    private RowMapper<Work> workRowMapper() {
        return (rs, rowNum) -> {
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

            Work work = Work.builder()
                .id(rs.getObject("id", Integer.class))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .createdDateTime(rs.getTimestamp("created_datetime", cal).toLocalDateTime())
                .build();

            return work;
        };
    }
}
