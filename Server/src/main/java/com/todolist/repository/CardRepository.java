package com.todolist.repository;

import java.util.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.todolist.domain.Card;
import com.todolist.domain.dto.CardInformationDto;
import com.todolist.domain.dto.CardPatchDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class CardRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Card> findAll(Integer userId) {
        String sql = "SELECT * FROM card WHERE removed = FALSE and userId = :userId ORDER BY createdTime";
        SqlParameterSource namedParameters = new MapSqlParameterSource("userId", userId);

        return namedParameterJdbcTemplate.query(sql, namedParameters, cardRowMapper());
    }

    public void delete(Integer cardId) {
        String sql = "UPDATE card SET removed = true WHERE cardId = :cardId";
        SqlParameterSource namedParameters = new MapSqlParameterSource("cardId", cardId);

        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public Integer save(Card card) {
        String sql = "INSERT INTO card (userId, cardTitle, cardContent, boardName, createdTime) VALUES (:userId, :cardTitle, :cardContent, :boardName, :createdTime)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card), keyHolder, new String[]{"cardId"});

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public CardInformationDto findCard(Integer cardId) {
        String sql = "SELECT * FROM card WHERE cardId = :cardId";
        SqlParameterSource namedParameter = new MapSqlParameterSource("cardId", cardId);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameter, cardInformationDtoRowMapper());
    }

    public void patch(Integer cardId, CardPatchDto cardPatchDto) {
        String sql = "UPDATE card SET cardTitle = :cardTitle, cardContent = :cardContent WHERE cardId = :cardId";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("cardId", cardId);
        parameterSource.addValue("cardTitle", cardPatchDto.getCardTitle());
        parameterSource.addValue("cardContent", cardPatchDto.getCardContent());

        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    private RowMapper<CardInformationDto> cardInformationDtoRowMapper() {
        return (resultSet, rowNum) -> new CardInformationDto(
            resultSet.getInt("cardId"),
            resultSet.getString("cardTitle"),
            resultSet.getString("cardContent"),
            resultSet.getString("boardName")
        );
    }

    private RowMapper<Card> cardRowMapper() {
        return (resultSet, rowNum) ->
            Card.builder()
                .cardId(resultSet.getInt("cardId"))
                .userId(resultSet.getInt("userId"))
                .cardTitle(resultSet.getString("cardTitle"))
                .cardContent(resultSet.getString("cardContent"))
                .boardName(resultSet.getString("boardName"))
                .createdTime(resultSet.getTimestamp("createdTime").toLocalDateTime())
                .build();
    }
}
