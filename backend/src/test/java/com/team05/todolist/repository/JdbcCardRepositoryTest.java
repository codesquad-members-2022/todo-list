package com.team05.todolist.repository;


import com.team05.todolist.domain.Card;
import java.util.List;
import javax.sql.DataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.jdbc.Sql;

@JdbcTest
@Sql({"/schema.sql", "/data.sql"})
class JdbcCardRepositoryTest {

	private CardRepository cardRepository;

	@Autowired
	DataSource dataSource;

	@BeforeEach
	void before() {
		cardRepository = new JdbcCardRepository(dataSource);
	}

	@Test
	@DisplayName("섹션을 인자로 받으면, 섹션 별 카드 개수를 조회한다.")
	void findNumberOfCards() {
		String section = "doing";
		Integer numberOfCards = cardRepository.findNumberOfCards(section);
		Assertions.assertThat(numberOfCards).isEqualTo(3);
	}
}
