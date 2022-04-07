package com.todolist.repository;

import java.sql.Types;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.todolist.domain.Card;
import com.todolist.domain.dto.CardInformationDto;
import com.todolist.domain.dto.CardPatchDto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class CardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final KeyHolderGenerator keyHolderGenerator;

    public List<Card> findAll(Integer userId) {
        // String sql = "SELECT * FROM card WHERE removed = FALSE and userId = ? ORDER BY createdTime";
        String sql = "SELECT * FROM card WHERE removed = FALSE and userId = ? ORDER BY createdTime DESC";
        return jdbcTemplate.query(sql, cardRowMapper(), userId);
    }

    public Integer delete(Integer cardId) {
        String sql = "UPDATE card Set removed = true WHERE cardId = ?";
        jdbcTemplate.update(sql, cardId);
        return cardId;
    }

    public Integer save(Card card) {
        String sql = "INSERT INTO card (userId, cardTitle, cardContent, boardName, createdTime) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = keyHolderGenerator.getKeyHolder();

        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql,
            Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.TIMESTAMP);

        pscFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(
            Arrays.asList(card.getUserId(), card.getCardTitle(), card.getCardContent(), card.getBoardName(), card.getCreatedTime()));

        jdbcTemplate.update(psc, keyHolder);

        // jdbcTemplate.update(sql, card.getUserId(), card.getCardTitle(), card.getCardContent(), card.getBoardName(),
        //     card.getCreatedTime(), keyHolder, new String[] {"cardId"});
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public CardInformationDto findCard(Integer cardId) {
        String sql = "SELECT * FROM card WHERE cardId = ?";

        // RowMapper<Card> 는 타입이 달라서 사용할 수 없으므로 새로운 RowMapper<CardInformation> 생성해서 사용
        return jdbcTemplate.queryForObject(sql, cardInformationDtoRowMapper(), cardId);
    }

    public void patch(Integer cardId, CardPatchDto cardPatchDto) {
        String sql = "UPDATE card Set cardTitle = ?, cardContent = ? WHERE cardId = ?";
        jdbcTemplate.update(sql, cardPatchDto.getCardTitle(), cardPatchDto.getCardContent(), cardId);
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
