package com.todolist.project.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import com.todolist.project.domain.CardStatus;
import com.todolist.project.web.dto.CardListDto;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;


@JdbcTest
@Sql({"/schema.sql", "/data.sql"})
class CardRepositoryTest {

	@Autowired
	public DataSource dataSource;

	public CardRepository cardRepository;

	@BeforeEach
	void setUp() {
		cardRepository = new CardRepository(dataSource);
	}

	@Test
	void findCardById() {
		Card cardById = cardRepository.findCardById(1L);
		assertThat(cardById.getId()).isEqualTo(1L);
		assertThat(cardById.getTitle()).isEqualTo("테스트1");
		assertThat(cardById.getWriter()).isEqualTo("얀");
	}

	@Test
	void findCardsByStatus() {
		List<CardListDto> todo = cardRepository.findCardsByStatus("TODO");
		assertThat(todo.size()).isEqualTo(1);
	}

	@Test
	void findAll() {
		List<CardListDto> dtoList = cardRepository.findAll();
		assertThat(dtoList.size()).isEqualTo(4);
	}

	@Test
	void add() {
		Card card = new Card(5, "test5", "테스트입니다", "tester1", CardStatus.TODO);
		int add = cardRepository.add(card);
		assertThat(add).isEqualTo(1);
	}

	@Test
	void remove() {
		cardRepository.remove(1L);
		List<CardListDto> dtoList = cardRepository.findAll();
		assertThat(dtoList.size()).isEqualTo(3L);
	}

	@Test
	void update() {
		Card card = new Card(4, "테스트5", "테스트입니다", CardStatus.DONE);
		int update = cardRepository.update(1L, card);
		assertThat(update).isEqualTo(1);
	}
}
