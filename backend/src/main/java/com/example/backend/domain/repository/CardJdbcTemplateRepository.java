package com.example.backend.domain.repository;

import com.example.backend.domain.Card;
import com.example.backend.domain.Column;
import com.example.backend.web.dto.CardMoveRequestDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CardJdbcTemplateRepository implements CardRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public CardJdbcTemplateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("CARD")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Column findAllDesc() {
        return null;
    }

    @Override
    public Long save(Card card) {
        String columnName = card.getColumnName();
        Integer orderIndex = jdbcTemplate.queryForObject(
                "SELECT count(*) FROM CARD WHERE column_name = '" + columnName + "'", Integer.class);

        Map<String, Object> params = new HashMap<>();
        params.put("title", card.getTitle());
        params.put("content", card.getContent());
        params.put("column_name", columnName);
        params.put("author_system", card.getAuthorSystem());
        params.put("order_index", orderIndex);
        params.put("deleted", 0);
        return simpleJdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    public Optional<Card> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Long deleteById(Long id) {
        return null;
    }

    @Override
    public Long move(CardMoveRequestDto dto) {
        return null;
    }
}
