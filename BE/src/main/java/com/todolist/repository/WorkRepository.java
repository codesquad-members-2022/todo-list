package com.todolist.repository;

import com.todolist.domain.Category;
import com.todolist.domain.Work;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WorkRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public WorkRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Category> findAllCategories() {
        return jdbc.query("SELECT id, name FROM category", categoryRowMapper());
    }

    public List<Work> findAllWorksByCategoryId(int categoryId) {
        return jdbc.query("SELECT id, category_id, title, content, user_id, created_date FROM work WHERE category_id = :id ORDER BY created_date DESC",
            Collections.singletonMap("id", categoryId), workRowMapper());
    }

    private RowMapper<Work> workRowMapper() {
        return (rs, rowNum) -> {
            Work work = new Work(rs.getObject("id", Integer.class),
                rs.getObject("category_id", Integer.class),
                rs.getString("title"),
                rs.getString("content"),
                rs.getString("user_id"),
                rs.getTimestamp("created_date").toLocalDateTime());

            return work;
        };
    }

    private RowMapper<Category> categoryRowMapper() {
        return (rs, rowNum) -> {
            Category category = new Category(rs.getObject("id", Integer.class), rs.getString("name"));

            return category;
        };
    }
}
