package com.example.backend.repository.card;

import static com.example.backend.utils.TimeUtils.dateTimeOf;

import com.example.backend.domain.card.Card;

import com.example.backend.domain.card.CardType;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Configuration
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

    public Map<String, Object> toParamMap(Card card) {
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

    public SqlParameterSource getUpdateParameterSource(Card card) {
        return new MapSqlParameterSource()
                .addValue("id", card.getId())
                .addValue("title", card.getTitle())
                .addValue("content", card.getContent())
                .addValue("position", card.getPosition())
                .addValue("cardType", card.getCardType().toString())
                .addValue("lastModifiedAt", card.getLastModifiedAt());
    }

    public RowMapper<Card> getMapper() {
        return mapper;
    }
}
