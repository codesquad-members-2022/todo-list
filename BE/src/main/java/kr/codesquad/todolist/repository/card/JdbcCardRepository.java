package kr.codesquad.todolist.repository.card;

import kr.codesquad.todolist.domain.Card;
import kr.codesquad.todolist.domain.Section;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Repository
public class JdbcCardRepository implements CardRepository {

    private static final Long ORDER_INTERVAL = 1000L;
    private static final Long EMPTY_NUMBER = 0L;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcCardRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Card save(Card card) {
        if (card.hasId()) {
            return update(card);
        }
        return insert(card);
    }

    @Override
    public Optional<Card> findById(Long id) {
        String sql = "select id, member_id, section_id, subject, contents, order_index, created_at, updated_at, deleted " +
                "from card where id = :id and deleted = false";
        try {
            Card card = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), cardRowMapper());
            return Optional.ofNullable(card);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Card> findAll() {
        String sql = "select id, member_id, section_id, subject, contents, order_index, created_at, updated_at, deleted from card where deleted = false";
        return namedParameterJdbcTemplate.query(sql, cardRowMapper());
    }

    @Override
    public boolean delete(Long id) {
        String sql = "update card set deleted = true where id = :id";

        return namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id)) == 1;
    }

   @Override
    public boolean move(Integer targetSectionId, Long targetCardId, Long movingCardId) {
       String sql = "update card set section_id = :sectionId, order_index = :orderIndex where id = :id and deleted = false";
       Long orderIndex = generateOrderIndex(targetSectionId, targetCardId);

       MapSqlParameterSource parameterSource = new MapSqlParameterSource()
               .addValue("sectionId", targetSectionId)
               .addValue("orderIndex", orderIndex)
               .addValue("id", movingCardId);

       return namedParameterJdbcTemplate.update(sql, parameterSource) == 1;
    }

    @Override
    public List<Card> findBySectionId(Integer sectionId) {
        String sql = "select id, member_id, section_id, subject, contents, order_index, created_at, updated_at, deleted from card " +
                "where deleted = false and section_id = :sectionId order by order_index desc";

        return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource("sectionId", sectionId), cardRowMapper());
    }

    @Override
    public Optional<Section> findSection(Integer sectionId) {
        String sql = "select id, name from section where id = :id";
        try {
            Section section = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", sectionId), sectionRowMapper());
            return Optional.ofNullable(section);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Section> findSections() {
        String sql = "select id, name from section";
        return namedParameterJdbcTemplate.query(sql, sectionRowMapper());
    }

    private Long generateOrderIndex(Integer targetSectionId, Long targetCardId) {

        if (targetCardId < EMPTY_NUMBER) { // targetCardId가 0이하이면 section의 가장 아래로 이동한다.
            return findMinOrderIndex(targetSectionId) / 2;
        }

        Card targetCard = findById(targetCardId).orElseThrow(() -> new NoSuchElementException("ERROR!! NOT FOUND"));
        Long foundOrderIndex = findMaxOrderIndex(targetSectionId);
        if(targetCard.isSameOrderIndex(foundOrderIndex)) {
            return foundOrderIndex + ORDER_INTERVAL;
        }

        Long targetOrderIndex = targetCard.getOrderIndex();
        Long previousOrderIndex = findPreviousOrderIndex(targetSectionId, targetOrderIndex);
        return (targetOrderIndex + previousOrderIndex) / 2;
    }

    private RowMapper<Card> cardRowMapper() {
        return (rs, rowNum) -> Card.of(
                rs.getLong("id"),
                rs.getString("member_id"),
                rs.getInt("section_id"),
                rs.getString("subject"),
                rs.getString("contents"),
                rs.getLong("order_index"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getTimestamp("updated_at").toLocalDateTime(),
                rs.getBoolean("deleted"));
    }

    private RowMapper<Section> sectionRowMapper() {
        return (rs, rowNum) -> new Section(
                rs.getInt("id"),
                rs.getString("name"));
    }

    private Card insert(Card card) {
        Long orderIndex = findMaxOrderIndex(card.getSectionId()) + ORDER_INTERVAL;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", card.getId())
                .addValue("memberId", card.getMemberId())
                .addValue("sectionId", card.getSectionId())
                .addValue("subject", card.getSubject())
                .addValue("contents", card.getContents())
                .addValue("orderIndex", orderIndex)
                .addValue("createdAt", card.getCreatedAt())
                .addValue("updatedAt", card.getUpdatedAt());

        String sql = "insert into card (member_id, section_id, subject, contents, order_index, created_at, updated_at) " +
                "values (:memberId, :sectionId, :subject, :contents, :orderIndex, :createdAt, :updatedAt)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, parameterSource, keyHolder);
        Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return Card.withIdAndOrderIndex(id, orderIndex, card);
    }

    private Card update(Card card) {
        String sql = "update card set subject = :subject, contents = :contents, updated_at = :updatedAt where id = :id";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(card));
        return card;
    }

    private Long findMaxOrderIndex(Integer sectionId) {
        String sql = "select max(order_index) from card where section_id = :sectionId and deleted = false";
        Long maxIndex = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("sectionId", sectionId), Long.class);
        if(maxIndex == null) {
            return EMPTY_NUMBER;
        }
        return maxIndex;
    }

    private Long findMinOrderIndex(Integer sectionId) {
        String sql = "select min(order_index) from card where section_id = :sectionId and deleted = false";
        Long minIndex = namedParameterJdbcTemplate.queryForObject(sql, new MapSqlParameterSource("sectionId", sectionId), Long.class);
        if(minIndex == null) {
            return EMPTY_NUMBER;
        }
        return minIndex;
    }

    private Long findPreviousOrderIndex(Integer sectionId, Long orderIndex) {
        String sql = "select min(order_index) from card where section_id = :sectionId and deleted = false and order_index > :orderIndex";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("sectionId", sectionId)
                .addValue("orderIndex", orderIndex);

        return namedParameterJdbcTemplate.queryForObject(sql, parameterSource, Long.class);
    }
}
