package com.example.backend.repository.card.jdbc;


import com.example.backend.domain.card.Card;
import com.example.backend.repository.card.CardRepository;
import com.example.backend.repository.card.CardRepositoryHelper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

@Repository
public class CardJdbcRepository implements CardRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CardRepositoryHelper repositoryHelper;

    public CardJdbcRepository(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.repositoryHelper = new CardRepositoryHelper();
    }
    @Override
    public Card save(Card card) {
        String query = "insert card (id, writer, position, title, content, card_type, created_at, last_modified_at, `visible`, member_id)" +
                "values (:id, :writer, :position, :title, :content, :cardType, :createdAt, :lastModifiedAt, :visible, :memberId)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String, Object> params = repositoryHelper.getParams(card);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(params);
        jdbcTemplate.update(query, mapSqlParameterSource, keyHolder);
        return card;
    }

    @Override
    public List<Card> findAll() {
        String query = "select id, " +
                "writer, " +
                "position, " +
                "title, " +
                "content, " +
                "card_type, " +
                "created_at, " +
                "last_modified_at, " +
                "visible, " +
                "member_id " +
                "from card where visible = true";
        RowMapper<Card> mapper = repositoryHelper.getMapper();
        return jdbcTemplate.query(query, mapper);
    }

    @Override
    public Optional<Card> findById(Long id) {
        String query = "select id, " +
                "writer, " +
                "position, " +
                "title, " +
                "content, " +
                "card_type, " +
                "created_at, " +
                "last_modified_at, " +
                "visible, " +
                "member_id " +
                "from card where id = :id";
        Map<String, Object> params = Collections.singletonMap("id", id);
        RowMapper<Card> mapper = repositoryHelper.getMapper();
        return Optional.ofNullable(jdbcTemplate.queryForObject(query, params, mapper));
    }

    @Override
    public Card update(Card card) {
        String query = "update todo_list.card set title=:title, content=:content, last_modified_at=:lastModifiedAt where id=:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", card.getId())
                .addValue("title", card.getTitle())
                .addValue("content", card.getContent())
                .addValue("lastModifiedAt", LocalDateTime.now());
        jdbcTemplate.update(query, sqlParameterSource);
        return card;
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        String query = "update todo_list.card set visible=false where id=:id";
        jdbcTemplate.update(query, namedParameters);
    }
}
