package com.todolist.repository;

import com.todolist.domain.Category;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public CategoryRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Category> findAll(String userId) {
        return jdbc.query("SELECT id, name FROM category WHERE user_id = :userId", Collections.singletonMap("userId", userId), categoryRowMapper());
    }

    public String findNameById(Integer id) {
        return jdbc.queryForObject("SELECT name FROM category WHERE id = :id", Collections.singletonMap("id", id), String.class);
    }

    private RowMapper<Category> categoryRowMapper() {
        return (rs, rowNum) -> {
            Category category = new Category(
                rs.getObject("id", Integer.class),
                rs.getString("name")
            );

            return category;
        };
    }
}
