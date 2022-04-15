package com.example.backend.repository.card.jdbc;


import com.example.backend.domain.card.Card;
import com.example.backend.domain.card.CardType;
import com.example.backend.repository.card.CardRepository;
import com.example.backend.repository.card.CardRepositoryQueryHelper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.backend.repository.card.CardRepositoryQueryHelper.*;
import static java.time.LocalDateTime.now;

@Repository
public class CardJdbcRepository implements CardRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;
    private final CardRepositoryQueryHelper queryHelper;

    public CardJdbcRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, CardRepositoryQueryHelper queryHelper) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(CARD)
                .usingGeneratedKeyColumns(ID);
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.queryHelper = queryHelper;
    }

    @Override
    public Card save(Card card) {
        Map<String, Object> params = queryHelper.getSaveParamMap(card);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource(params);
        Long id = jdbcInsert.executeAndReturnKey(sqlParameterSource).longValue();
        return new CardBuilder()
                .id(id)
                .writer(card.getWriter())
                .position(card.getPosition())
                .title(card.getTitle())
                .content(card.getContent())
                .cardType(card.getCardType())
                .createdAt(now())
                .lastModifiedAt(now())
                .visible(card.isVisible())
                .memberId(card.getMemberId())
                .build();
    }

    @Override
    public List<Card> findAll() {
        String query = "SELECT id, writer, position, title, content, card_type, created_at, last_modified_at, visible, member_id FROM card WHERE visible = TRUE";
        return namedParameterJdbcTemplate.query(query, generalMapper);
    }

    @Override
    public Optional<Card> findById(Long id) {
        String query = "SELECT id, writer, position, title, content, card_type, created_at, last_modified_at, visible, member_id FROM card WHERE id = :id";
        Map<String, Object> params = Collections.singletonMap(ID, id);
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(query, params, generalMapper));
    }

    @Override
    public Card update(Card card) {
        String query = "UPDATE card SET title=:title, content=:content, card_type=:cardType, position=:position, last_modified_at=:lastModifiedAt WHERE id=:id AND visible = TRUE";
        SqlParameterSource sqlParameterSource = queryHelper.getUpdateParamSource(card);
        namedParameterJdbcTemplate.update(query, sqlParameterSource);
        return card;
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue(ID, id);
        String deleteQuery = "UPDATE card SET visible = FALSE WHERE id=:id AND visible = TRUE";
        int result = namedParameterJdbcTemplate.update(deleteQuery, namedParameters);
        if (result == 0) {
            throw new IllegalArgumentException("이미 삭제된 카드입니다.");
        }
        Card card = findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카드입니다."));
        String orderQuery = "UPDATE card SET position = position-1 WHERE card_type = ? AND position > ?";
        jdbcTemplate.update(orderQuery, card.getCardTypeName(), card.getPosition());
    }

    @Override
    public Card changePosition(Card card, CardType cardType) {
        return null;
    }

    private static class CardBuilder {
        private Long id;
        private String writer;
        private Long position;
        private String title;
        private String content;
        private CardType cardType;
        private LocalDateTime createdAt;
        private LocalDateTime lastModifiedAt;
        private boolean visible;
        private Long memberId;

        private CardBuilder() {
        }

        ;

        private CardBuilder id(Long id) {
            this.id = id;
            return this;
        }

        private CardBuilder writer(String writer) {
            this.writer = writer;
            return this;
        }

        private CardBuilder position(Long position) {
            this.position = position;
            return this;
        }

        private CardBuilder title(String title) {
            this.title = title;
            return this;
        }

        private CardBuilder content(String content) {
            this.content = content;
            return this;
        }

        private CardBuilder cardType(CardType cardType) {
            this.cardType = cardType;
            return this;
        }

        private CardBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        private CardBuilder lastModifiedAt(LocalDateTime lastModifiedAt) {
            this.lastModifiedAt = lastModifiedAt;
            return this;
        }

        private CardBuilder visible(boolean visible) {
            this.visible = visible;
            return this;
        }

        private CardBuilder memberId(Long memberId) {
            this.memberId = memberId;
            return this;
        }

        private Card build() {
            return new Card(id, writer, position, title, content, cardType, createdAt, lastModifiedAt, visible, memberId);
        }
    }
}
