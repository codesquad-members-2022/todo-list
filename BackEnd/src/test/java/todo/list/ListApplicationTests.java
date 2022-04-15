package todo.list;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import todo.list.domain.CardStatus;

//@SpringBootTest
class ListApplicationTests {

	@Test
	void contextLoads() {
		CardStatus status = null;
		System.out.println(CardStatus.TODO.nameOrNull());
		System.out.println(status.nameOrNull());
	}

}
