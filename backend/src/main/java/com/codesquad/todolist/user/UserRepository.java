package com.codesquad.todolist.user;

import com.codesquad.todolist.util.KeyHolderFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final KeyHolderFactory keyHolderFactory;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate,
        KeyHolderFactory keyHolderFactory) {
        this.jdbcTemplate = jdbcTemplate;
        this.keyHolderFactory = keyHolderFactory;
    }

    public User create(User user) {
        KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

        jdbcTemplate.update("insert into `user` (user_name) values (:userName)",
            new BeanPropertySqlParameterSource(user), keyHolder);

        if (keyHolder.getKey() != null) {
            user.setUserId(keyHolder.getKey().intValue());
        }
        return user;
    }
}
