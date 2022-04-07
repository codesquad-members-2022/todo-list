package com.todolist.project.domain.card;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CardRepository {

    private final JdbcTemplate jdbcTemplate;
    public CardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private final static String DELETE_CARD_SQL = "delete from card where id = ?";
    private final static String INSERT_CARD_SQL = "insert into card(title, contents, writer, createTime, status) value (?,?,?,?,?)";


    public void add(Card card){
        jdbcTemplate.update(INSERT_CARD_SQL, card.getTitle(), card.getContents(), card.getWriter(), card.getCreatedTime(), card.getCardStatus());
    }

    public void remove(int id){
        jdbcTemplate.update(DELETE_CARD_SQL, id);
    }
}
