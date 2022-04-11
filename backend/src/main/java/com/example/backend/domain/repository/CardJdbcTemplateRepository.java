package com.example.backend.domain.repository;

import com.example.backend.domain.Card;
import com.example.backend.domain.ColumnName;
import com.example.backend.web.dto.CardListResponseDto;
import com.example.backend.web.dto.CardMoveRequestDto;
import com.example.backend.web.dto.Column;
import com.example.backend.web.dto.Columns;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
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
    public Columns findAllDesc() {
        Columns columns = new Columns();
        for (ColumnName column : ColumnName.values()) {
            String columnName = column.getName();
            Column resultColumn = new Column(columnName);

            resultColumn.addCards(findResponseDtosByColumnName(columnName));
            columns.addColumn(resultColumn);
        }
        return columns;
    }

    private List<CardListResponseDto> findResponseDtosByColumnName(String columnName) {
        return jdbcTemplate.query("SELECT id, title, content, author_system FROM CARD WHERE COLUMN_NAME = ? ORDER BY ORDER_INDEX",
                (rs, rowNum) -> {
                    long id = rs.getLong("id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String authorSystem = rs.getString("author_system");
                    return new CardListResponseDto(id, title, content, authorSystem);
                }, columnName);
    }

    @Override
    public Long save(Card card) {
        String columnName = card.getColumnName();
        Integer orderIndex = getOrderIndex(columnName);

        Map<String, Object> params = new HashMap<>();
        params.put("title", card.getTitle());
        params.put("content", card.getContent());
        params.put("column_name", columnName);
        params.put("author_system", card.getAuthorSystem());
        params.put("order_index", orderIndex);
        params.put("deleted", false);
        return simpleJdbcInsert.executeAndReturnKey(params).longValue();
    }

    private Integer getOrderIndex(String columnName) {
        return jdbcTemplate.queryForObject(
                "SELECT count(*) FROM CARD WHERE column_name = ?", Integer.class, columnName);
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
