package team07.todolist.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import team07.todolist.domain.Card;
import team07.todolist.dto.RequestCard;

@Repository
public class DataBaseCardRepository implements CardRepository {

	private final JdbcTemplate jdbcTemplate;
	private final RowMapper<Card> rowMapper;

	public DataBaseCardRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.rowMapper = (rs, rowNum) -> new Card(
			rs.getLong("id"),
			rs.getString("userid"),
			rs.getString("title"),
			rs.getString("content"),
			rs.getInt("sequence"),
			rs.getInt("status"),
			rs.getBoolean("is_deleted")
		);
	}

	@Override
	public Card findById(Long id) {
		List<Card> result = jdbcTemplate.query("SELECT * FROM TODO_CARD WHERE id = ?", rowMapper, id);
		return result.stream().findAny()
			.orElseThrow(() -> new IllegalStateException("존재하지 않는 id 입니다"));
	}

	@Override
	public Card save(Card card) {

		Integer sequence = countTotalSequence(card.getStatus());

		if (card.getSequence() != sequence + 1) {
			throw new IllegalStateException("부적절한 sequence 값입니다.");
		}

		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		jdbcInsert.withTableName("TODO_CARD").usingGeneratedKeyColumns("id");

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("userId", card.getUserId());
		parameters.put("title", card.getTitle());
		parameters.put("content", card.getContent());
		parameters.put("sequence", card.getSequence());
		parameters.put("status", card.getStatus());
		parameters.put("is_deleted", card.isDeleted());

		Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
		return new Card(card, key.longValue());
	}

	@Override
	public Card delete(Long id) {
		int update = jdbcTemplate.update("UPDATE TODO_CARD SET is_deleted = true WHERE id = ? AND is_deleted = false", id);
		if (update == 0) {
			throw new IllegalStateException("이미 삭제된 데이터입니다.");
		}

		Card card = findById(id);
		jdbcTemplate.update(
			"UPDATE TODO_CARD SET sequence = sequence - 1 WHERE status = ? AND sequence > ?", card.getStatus(), card.getSequence());

		return findById(id);
	}

	@Override
	public Card updateStatusAndRow(Long id, RequestCard requestCard) {
		Card originCard = findById(id);

		Integer sequence = countTotalSequence(requestCard.getStatus());

		if (requestCard.getSequence() > sequence + 1) {
			throw new IllegalStateException("부적절한 sequence 값입니다.");
		}

		jdbcTemplate.update("UPDATE TODO_CARD SET sequence = sequence - 1 WHERE status = ? AND sequence > ?", originCard.getStatus(), originCard.getSequence());
		jdbcTemplate.update("UPDATE TODO_CARD SET sequence = sequence + 1 WHERE status = ? AND sequence >= ?", requestCard.getStatus(), requestCard.getSequence());
		jdbcTemplate.update("UPDATE TODO_CARD SET status = ?, sequence = ? WHERE id = ?", requestCard.getStatus(), requestCard.getSequence(), id);

		return findById(id);
	}

	@Override
	public Card updateText(Long id, Card card) {
		jdbcTemplate.update("UPDATE TODO_CARD SET title = ?, content = ? WHERE id = ?",	card.getTitle(), card.getContent(), id);
		return card;
	}

	@Override
	public List<Card> findAll() {
		return jdbcTemplate.query("SELECT * FROM TODO_CARD", rowMapper);
	}

	@Override
	public void reset() {
		jdbcTemplate.execute("TRUNCATE TABLE TODO_CARD");
	}

	private Integer countTotalSequence(int status) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM TODO_CARD WHERE status = ? AND is_deleted = false", Integer.class, status);
	}

}
