package com.ijava.todolist.user.repository;

import com.ijava.todolist.user.domain.User;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            String findByIdSql = "SELECT id,name,created_date,modified_date FROM users WHERE id = ?";

            User user = jdbcTemplate.queryForObject(findByIdSql, userRowMapper(), id);

            log.debug(findByIdSql, user);

            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getTimestamp("created_date").toLocalDateTime(),
            rs.getTimestamp("modified_date").toLocalDateTime()
        );
    }
}
