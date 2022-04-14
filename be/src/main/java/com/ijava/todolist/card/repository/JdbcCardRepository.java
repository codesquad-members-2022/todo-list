package com.ijava.todolist.card.repository;

import com.ijava.todolist.card.domain.Card;
import com.ijava.todolist.card.exception.CardNotSavedException;
import com.ijava.todolist.common.domain.Deleted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.sql.Delete;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcCardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        simpleJdbcInsert.withTableName("card").usingGeneratedKeyColumns("id");
    }

    @Override
    public Card save(Card card) {
        if (card.getId() == null) {
            return insertCard(card);
        }

        return updateCard(card);
    }

    private Card insertCard(Card card) {
        try {
            Number number = simpleJdbcInsert.executeAndReturnKey(getCardSqlParameterSource(card));

            log.debug(simpleJdbcInsert.getInsertString());

            card.setId(number.longValue());

            return card;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CardNotSavedException();
        }
    }

    private Card updateCard(Card card) {
        try {
            String updateCardSql = "UPDATE card SET \n" +
                    "    title = :title, \n" +
                    "    content = :content, \n" +
                    "    columns_id = :columnsId, \n" +
                    "    deleted = :deleted, \n" +
                    "    modified_date = :modifiedDate \n" +
                    "  WHERE id = :id";

            log.debug(updateCardSql);

            int update = namedParameterJdbcTemplate.update(updateCardSql, getCardSqlParameterSource(card));

            if (update > 0) {
                return card;
            }

            throw new CardNotSavedException();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CardNotSavedException();
        }
    }

    private SqlParameterSource getCardSqlParameterSource(Card card) {
        return new MapSqlParameterSource()
                .addValue("id", card.getId())
                .addValue("title", card.getTitle())
                .addValue("content", card.getContent())
                .addValue("columnsId", card.getColumnsId())
                .addValue("deleted", card.getDeleted().name())
                .addValue("createdDate", card.getCreatedDate())
                .addValue("modifiedDate", card.getModifiedDate());
    }

    @Override
    public Optional<Card> findById(Long id) {
        try {
            String findByIdSql = "SELECT id\n " +
                    "     , columns_id\n " +
                    "     , title\n " +
                    "     , content\n " +
                    "     , deleted\n " +
                    "     , created_date\n " +
                    "     , modified_date \n " +
                    " FROM card \n " +
                    "WHERE id = :id\n " +
                    "  AND deleted = :deleted";

            log.debug(findByIdSql);

            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("id", id)
                    .addValue("deleted", Deleted.N.name());

            return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(findByIdSql, sqlParameterSource, cardRowMapper()));
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
                    ", deleted\n" +
                    ", created_date\n" +
                    ", modified_date\n" +
                    "FROM card\n" +
                    "WHERE columns_id = :columnsId\n" +
                    "  AND deleted = :deleted";

            log.debug(findByColumnIdSql);

            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("columnsId", columnsId)
                    .addValue("deleted", Deleted.N.name());

            return Optional.of(namedParameterJdbcTemplate.query(findByColumnIdSql, sqlParameterSource, cardRowMapper()));
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
        String getCountSql = "SELECT count(*) FROM card WHERE columns_id = ? AND deleted = ?";

        log.debug(getCountSql);

        return Optional.ofNullable(jdbcTemplate.queryForObject(getCountSql, Integer.class, columnsId, Deleted.N));
    }

    private RowMapper<Card> cardRowMapper() {
        return (rs, rowNum) -> Card.builder()
                .id(rs.getLong("id"))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .columnsId(rs.getLong("columns_id"))
                .deleted(Deleted.from(rs.getString("deleted")))
                .createdDate(rs.getTimestamp("created_date").toLocalDateTime())
                .modifiedDate(rs.getTimestamp("modified_date").toLocalDateTime())
                .build();
    }
}
