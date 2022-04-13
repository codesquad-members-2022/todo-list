package com.example.backend.repository.history;

import com.example.backend.controller.history.dto.HistoryRead;
import com.example.backend.domain.card.CardType;
import com.example.backend.domain.history.Action;
import com.example.backend.domain.history.History;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.example.backend.utils.TimeUtils.dateTimeOf;

@Configuration
public class HistoryQueryHelper {

    private static final String ID = "id";
    private static final String CONTENT = "content";
    private static final String CREATED_AT = "created_at";
    private static final String LAST_MODIFIED_AT = "last_modified_at";
    private static final String ACTION = "action";
    public static final String MEMBER_ID = "member_id";
    public static final String CARD_ID = "card_id";
    private static final String FONT = "font";
    private static final String VISIBLE = "visible";

    public Map<String, Object> getParams(History history) {
        return toParamMap(history);
    }

    private Map<String, Object> toParamMap(History history) {
        return new HashMap<>() {{
            put(ID, history.getId());
            put(CONTENT, history.getContent());
            put(CREATED_AT, history.getCreatedAt());
            put(ACTION, history.getAction().name());
            put(MEMBER_ID, history.getMemberId());
            put(CARD_ID, history.getCardId());
            put(FONT, history.getFont());
            put(VISIBLE, true);
        }};
    }

    public RowMapper<HistoryRead> getMapper() {
        return mapper;
    }
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Action action;
    private Long memberId;
    private Long cardId;
    private String author;
    private CardType cardType;
    private static final RowMapper<HistoryRead> mapper = (rs, rowNum) ->
            new HistoryRead(
                    rs.getLong(ID),
                    rs.getString(CONTENT),
                    dateTimeOf(rs.getTimestamp(CREATED_AT)),
//                    dateTimeOf(rs.getTimestamp(LAST_MODIFIED_AT)),
                    rs.getString(ACTION),
                    rs.getLong(MEMBER_ID),
                    rs.getLong(CARD_ID),
                    rs.getString("author"),
                    rs.getString("card_type")
            );
}
