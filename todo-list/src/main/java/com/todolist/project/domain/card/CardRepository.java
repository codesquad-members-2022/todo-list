package com.todolist.project.domain.card;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CardRepository {

    private final JdbcTemplate jdbcTemplate;
    public CardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    private final static String DELETE_CARD_SQL = "DELETE FROM card WHERE id = ?";
    private final static String INSERT_CARD_SQL = "INSERT INTO card(title, contents, writer, createTime, status) VALUE (?,?,?,?,?)";

    //TODO: ID값만 반환 -> simpleJDBC
    public int add(Card card){
        return jdbcTemplate.update(INSERT_CARD_SQL, card.getTitle(), card.getContents(), card.getWriter(), card.getCreatedTime(), card.getCardStatus());
    }

    public int remove(int id){
        return jdbcTemplate.update(DELETE_CARD_SQL, id);
    }
}
