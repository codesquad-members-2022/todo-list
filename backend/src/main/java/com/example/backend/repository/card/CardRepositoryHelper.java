package com.example.backend.repository.card;

import static com.example.backend.utils.TimeUtils.dateTimeOf;

import com.example.backend.domain.card.Card;

import com.example.backend.domain.card.CardType;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CardRepositoryHelper {

    private static RowMapper<Card> mapper = (rs, rowNum) ->
            new Card(
                    rs.getLong("id"),
                    rs.getString("writer"),
                    rs.getLong("position"),
                    rs.getString("title"),
                    rs.getString("content"),
                    CardType.valueOf(rs.getString("card_type")),
                    dateTimeOf(rs.getTimestamp("created_at")),
                    dateTimeOf(rs.getTimestamp("last_modified_at")),
                    rs.getBoolean("visible"),
                    rs.getLong("member_id")
            );

    public Map<String, Object> getParams(Card card) {
        return toParamMap(card);
    }

    private Map<String, Object> toParamMap(Card card) {
        return new HashMap<>() {{
            put("id", card.getId());
            put("writer", card.getWriter());
            put("position", card.getPosition());
            put("title", card.getTitle());
            put("content", card.getContent());
            put("cardType", card.getCardType().toString());
            put("createdAt", LocalDateTime.now());
            put("lastModifiedAt", LocalDateTime.now());
            put("visible", true);
            put("memberId", card.getMemberId());
        }};
    }

    public RowMapper<Card> getMapper() {
        return mapper;
    }
}
