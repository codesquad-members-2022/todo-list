package todo.list;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import todo.list.domain.CardStatus;
import todo.list.repository.CardRepository;

import java.time.LocalDateTime;

@SpringBootTest
class ListApplicationTests {

	@Autowired
	CardRepository cardRepository;

	@Test
	void contextLoads() {
		System.out.println("1" + LocalDateTime.now());

		for (int i = 0; i < 10000; i++) {
			cardRepository.findAllSameStatus(CardStatus.TODO);
		}

		System.out.println(LocalDateTime.now());
	}

	@Test
	@Transactional
	void contextLoads2() {
		System.out.println(LocalDateTime.now());

		for (int i = 0; i < 10000; i++) {
			cardRepository.findAllSameStatus(CardStatus.TODO);
		}

		System.out.println(LocalDateTime.now());
	}

}
