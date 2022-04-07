package com.todolist.db;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DBAccessTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void readDB() {
        String sql = "SELECT count(*) FROM user";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        assertThat(result).isEqualTo(9);
    }

}
