package codesquad.be.todoserver.repository;

import static org.assertj.core.api.Assertions.assertThat;

import codesquad.be.todoserver.domain.Todo;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DataJdbcTest
@DisplayName("API /api/todos/* 리포지토리 계층 단위 테스트")
class TodoRepositoryTest {

	private TodoRepository todoRepository;

	@Autowired
	public TodoRepositoryTest(JdbcTemplate jdbcTemplate) {
		this.todoRepository = new TodoJdbcRepository(jdbcTemplate);
	}

	@Test
	void 특정_투두리스트_조회_성공() {
		Todo todo = todoRepository.findById(1L).get();

		assertThat(todo.getId()).isEqualTo(1);
		assertThat(todo.getTitle()).isEqualTo("Github 공부하기");
		assertThat(todo.getContents()).isEqualTo("add, commit, push");
		assertThat(todo.getUser()).isEqualTo("sam");
		assertThat(todo.getStatus()).isEqualTo("todo");
	}

	@Test
	void 특정_투두리스트_조회_실패() {
		Optional<Todo> todo = todoRepository.findById(4444L);

		assertThat(todo.isPresent()).isFalse();
	}
}
