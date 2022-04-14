package com.example.backend.repository.card;

import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.example.backend.utils.TimeUtils.dateTimeOf;

@Configuration
public class CardRepositoryHelper {

    private static final String ID = "id";
    private static final String WRITER = "writer";
    private static final String POSITION = "position";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String CARD_TYPE = "card_type";
    private static final String CREATED_AT = "created_at";
    private static final String LAST_MODIFIED_AT = "last_modified_at";
    private static final String VISIBLE = "visible";
    private static final String MEMBER_ID = "member_id";

    public static RowMapper<Card> mapper = (rs, rowNum) ->
            new Card(
                    rs.getLong(ID),
                    rs.getString(WRITER),
                    rs.getLong(POSITION),
                    rs.getString(TITLE),
                    rs.getString(CONTENT),
                    CardType.valueOf(rs.getString(CARD_TYPE)),
                    dateTimeOf(rs.getTimestamp(CREATED_AT)),
                    dateTimeOf(rs.getTimestamp(LAST_MODIFIED_AT)),
                    rs.getBoolean(VISIBLE),
                    rs.getLong(MEMBER_ID)
            );

    public Map<String, Object> getParamMap(Card card) {
        return new HashMap<>() {{
            put(ID, card.getId());
            put(WRITER, card.getWriter());
            put(POSITION, card.getPosition());
            put(TITLE, card.getTitle());
            put(CONTENT, card.getContent());
            put("cardType", card.getCardType().toString());
            put("createdAt", LocalDateTime.now());
            put("lastModifiedAt", LocalDateTime.now());
            put(VISIBLE, true);
            put("memberId", card.getMemberId());
        }};
    }

    public SqlParameterSource getUpdateParameterSource(Card card) {
        return new MapSqlParameterSource()
                .addValue(ID, card.getId())
                .addValue(TITLE, card.getTitle())
                .addValue(CONTENT, card.getContent())
                .addValue(POSITION, card.getPosition())
                .addValue("cardType", card.getCardType().toString())
                .addValue("lastModifiedAt", card.getLastModifiedAt());
    }
}
