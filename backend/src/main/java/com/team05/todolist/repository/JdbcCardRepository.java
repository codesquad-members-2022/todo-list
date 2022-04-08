package com.team05.todolist.repository;

import com.team05.todolist.domain.Card;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCardRepository implements CardRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public JdbcCardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("card")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(Card card) {
        Map<String, Object> params = getSaveParams(card);
        simpleJdbcInsert.executeAndReturnKey(params).intValue();
    }

    private Map<String, Object> getSaveParams(Card card) {
        Map<String, Object> params = new HashMap();
        params.put("order_index", card.getOrderIndex());
        params.put("delete_yn", card.getDeleteYN());
        params.put("title", card.getTitle());
        params.put("content", card.getContent());
        params.put("section", card.getSectionType());
        return params;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Card> findAll() {
        return null;
    }

    @Override
    public Optional<Card> findById(int id) {
        return Optional.empty();
    }
}
