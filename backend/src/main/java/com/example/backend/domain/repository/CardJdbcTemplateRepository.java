package com.example.backend.domain.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.backend.domain.Card;
import com.example.backend.domain.ColumnName;
import com.example.backend.web.dto.CardListResponseDto;
import com.example.backend.web.dto.CardMoveRequestDto;
import com.example.backend.web.dto.Column;
import com.example.backend.web.dto.Columns;

@Repository
public class CardJdbcTemplateRepository implements CardRepository {
	private final JdbcTemplate jdbcTemplate;
	private final SimpleJdbcInsert simpleJdbcInsert;

	public CardJdbcTemplateRepository(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("CARD")
			.usingGeneratedKeyColumns("id");
	}

	@Override
	public Columns findAllDesc() {
		Columns columns = new Columns();
		for (ColumnName column : ColumnName.values()) {
			String columnName = column.getName();
			Column resultColumn = new Column(columnName);

			resultColumn.addCards(findResponseDtosByColumnName(columnName));
			columns.addColumn(resultColumn);
		}
		return columns;
	}

	private List<CardListResponseDto> findResponseDtosByColumnName(String columnName) {
		return jdbcTemplate.query(
			"SELECT id, title, content, author_system FROM CARD WHERE COLUMN_NAME = ? and DELETED = ? ORDER BY ORDER_INDEX DESC",
			(rs, rowNum) -> {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String authorSystem = rs.getString("author_system");
				return new CardListResponseDto(id, title, content, authorSystem);
			}, columnName, false);
	}

	@Override
	public CardListResponseDto save(Card card) {
		if (!Objects.isNull(card.getId())) {
			jdbcTemplate.update("UPDATE CARD SET title=?, content=? WHERE id=?", card.getTitle(), card.getContent(),
				card.getId());
			return new CardListResponseDto(card.getId(), card.getTitle(), card.getContent(), card.getAuthorSystem());
		}
		String columnName = card.getColumnName();
		Integer orderIndex = getOrderIndex(columnName);

		Map<String, Object> params = new HashMap<>();
		params.put("title", card.getTitle());
		params.put("content", card.getContent());
		params.put("column_name", columnName);
		params.put("author_system", card.getAuthorSystem());
		params.put("order_index", orderIndex);
		params.put("deleted", false);
		long id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
		return new CardListResponseDto(id, card.getTitle(), card.getContent(), card.getAuthorSystem());
	}

	private Integer getOrderIndex(String columnName) {
		return jdbcTemplate.queryForObject(
			"SELECT COUNT(*) FROM CARD WHERE COLUMN_NAME = ? and DELETED = ?", Integer.class, columnName, false);
	}

	@Override
	public Optional<Card> findById(Long id) {
		List<Card> cardList = jdbcTemplate.query(
			"SELECT id, title, content, column_name, author_system, order_index, deleted FROM CARD WHERE id = ?",
			(rs, rowNum) -> {
				long userId = rs.getLong("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String columnName = rs.getString("column_name");
				String authorSystem = rs.getString("author_system");
				long orderIndex = rs.getLong("order_index");
				boolean deleted = rs.getBoolean("deleted");
				return new Card.Builder().title(title)
					.content(content)
					.id(userId)
					.authorSystem(authorSystem)
					.columnName(columnName)
					.deleted(deleted)
					.orderIndex(orderIndex)
					.build();
			}, id);
		return cardList.stream().findAny();
	}

	@Override
	public Long delete(Card card) {
		Long id = card.getId();
		String columnName = card.getColumnName();
		Long orderIndex = card.getOrderIndex();

		jdbcTemplate.update("UPDATE CARD SET DELETED=? WHERE id=?", true, id);

		jdbcTemplate.update("UPDATE CARD SET ORDER_INDEX = ORDER_INDEX - 1" +
				" WHERE COLUMN_NAME = ? and ORDER_INDEX > ? and DELETED = ?", columnName, orderIndex, false);

		return id;
	}

	@Override
	public Long move(CardMoveRequestDto dto) {
		return null;
	}
}
