package com.hooria.todo.repository;

import com.hooria.todo.domain.Member;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    private final SimpleJdbcInsert insertJdbc;
    private final NamedParameterJdbcTemplate jdbc;
    private final RowMapper<Member> rowMapper;

    public MemberRepository(DataSource dataSource) {
        jdbc = new NamedParameterJdbcTemplate(dataSource);
        insertJdbc = new SimpleJdbcInsert(dataSource)
                .withTableName("member")
                .usingGeneratedKeyColumns("id");

        rowMapper = (rs, row) ->
                new Member(
                        rs.getLong("id"),
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("name")
                );
    }

    public Member insert(Member member) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        insertJdbc.executeAndReturnKey(params).intValue();
        return member;
    }

    public List<Member> findAll() {
        return jdbc.query(
                "select id, user_id, password, name from member",
                Collections.emptyMap(), rowMapper
        );
    }

    public Optional<Member> findById(String userId) {
        Member member = DataAccessUtils.singleResult(jdbc.query(
                "select id, user_id, password, name from member where user_id = :userId",
                new MapSqlParameterSource("userId", userId), rowMapper));

        return Optional.ofNullable(member);
    }
}
