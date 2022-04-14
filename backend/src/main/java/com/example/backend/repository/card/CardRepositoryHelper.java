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

    private static final String ID = "id";
    private static final String WRITER = "writer";
    private static final String POSITION = "position";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String CARD_TYPE = "card_type";
    private static final String CREATED_AT = "created_at";
    private static final String LAST_MODIFIED_AT = "lastModifiedAt";
    private static final String VISIBLE = "visible";
    private static final String MEMBER_ID = "memberId";

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

    public Map<String, Object> getParamMap(Card card) {
        return new HashMap<>() {{
            put(ID, card.getId());
            put(WRITER, card.getWriter());
            put(POSITION, card.getPosition());
            put(TITLE, card.getTitle());
            put(CONTENT, card.getContent());
            put(CARD_TYPE, card.getCardType().toString());
            put(CREATED_AT, LocalDateTime.now());
            put(LAST_MODIFIED_AT, LocalDateTime.now());
            put(VISIBLE, true);
            put(MEMBER_ID, card.getMemberId());
        }};
    }

    public SqlParameterSource getUpdateParameterSource(Card card) {
        return new MapSqlParameterSource()
                .addValue(ID, card.getId())
                .addValue(TITLE, card.getTitle())
                .addValue(CONTENT, card.getContent())
                .addValue(POSITION, card.getPosition())
                .addValue(CARD_TYPE, card.getCardType().toString())
                .addValue(LAST_MODIFIED_AT, card.getLastModifiedAt());
    }

    public RowMapper<Card> getMapper() {
        return mapper;
    }
}
