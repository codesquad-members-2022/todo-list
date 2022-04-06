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

    public void add(Card card){
        //TODO: 카드를 저장하는 쿼리 생성
    }

    public void remove(int id){
        //TODO: 카드 아이디로 카드 삭제
    }
}
