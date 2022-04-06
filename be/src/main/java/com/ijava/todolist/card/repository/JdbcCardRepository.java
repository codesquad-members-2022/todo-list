package com.ijava.todolist.card.repository;

import com.ijava.todolist.card.domain.Card;
import com.ijava.todolist.card.exception.CardNotSavedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcCardRepository implements CardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcCardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        simpleJdbcInsert.withTableName("card").usingGeneratedKeyColumns("id");
    }

    @Override
    public Card save(Card card) {
        try {
            Number number = simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(card));

            log.debug(simpleJdbcInsert.getInsertString());

            card.setId(number.longValue());

            return card;
        } catch (Exception e) {
            throw new CardNotSavedException();
        }
    }

    @Override
    public Optional<Card> findById(Long id) {
        try {
            String findByIdSql = "SELECT id, columns_id, title, content, created_date, modified_date FROM card WHERE id = ?";

            log.debug(findByIdSql);

            return Optional.ofNullable(jdbcTemplate.queryForObject(findByIdSql, cardRowMapper(), id));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Card>> findByColumnId(Long columnsId) {
        try {
            String findByColumnIdSql = "SELECT id\n" +
                    ", columns_id\n" +
                    ", title\n" +
                    ", content\n" +
                    ", created_date\n" +
                    ", modified_date\n" +
                    "FROM card\n" +
                    "WHERE columns_id = ?";

            log.debug(findByColumnIdSql);

            return Optional.of(jdbcTemplate.query(findByColumnIdSql, cardRowMapper(), columnsId));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public void deleteAll() {
        String deleteAllSql = "DELETE FROM card";

        log.debug(deleteAllSql);

        jdbcTemplate.update(deleteAllSql);
    }

    @Override
    public Optional<Integer> getCountOfCardsOnColumns(Long columnsId) {
        String getCountSql = "SELECT count(*) FROM card WHERE columns_id = ?";

        log.debug(getCountSql);

        return Optional.ofNullable(jdbcTemplate.queryForObject(getCountSql, Integer.class, columnsId));
    }

    private RowMapper<Card> cardRowMapper() {
        return (rs, rowNum) -> new Card(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getLong("columns_id"),
                rs.getTimestamp("created_date").toLocalDateTime(),
                rs.getTimestamp("modified_date").toLocalDateTime()
        );
    }
}
