package com.example.backend.controller.card;

import com.example.backend.domain.card.CardType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CardReadRepository {

    private Long id;
    private String title;
    private String content;
    private CardType cardType;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private boolean visible;
    private Long columnId;

    public List<TodoItem> findItemsTodoItems(CardType todo) {
        String query = "SELECT id, title, content, card_type, created_at, last_modified_at, column_id FROM CARDS WHERE "
        return null;
    }

    public List<HaveDoneItem> findHaveDoneItems(CardType done) {
        return null;
    }

    public List<DoingItem> findDoingItems(CardType doing) {
        return null;
    }


    private static final RowMapper<TodoItem> mapper = (rs, rowNum) ->
            new TodoItem(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("card_type"),

    );
//            new new TodoItem(
//                    rs.getString("member_login_id"),
//                    rs.getLong("id"),
//                    dateTimeOf(rs.getTimestamp("created_at")),
//                    rs.getString("content"),
//                    rs.getString("title")
//            );
}
