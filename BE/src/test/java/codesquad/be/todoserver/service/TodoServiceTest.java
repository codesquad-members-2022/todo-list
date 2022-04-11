package codesquad.be.todoserver.service;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.exception.NoSuchTodoFoundException;
import codesquad.be.todoserver.repository.TodoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DisplayName("API /api/todos/* 서비스 계층 단위 테스트")
class TodoServiceTest {

	private TodoRepository todoRepository = mock(TodoRepository.class);
	private TodoService todoService = new TodoService(todoRepository);

	@ParameterizedTest
	@ValueSource(strings = {"Github 공부하기", "add, commit, push", "sam", "todo"})
	void 특정_투두리스트_조회_성공(String input) {
		given(todoRepository.findById(1L))
			.willReturn(
				Optional.of(new Todo(1L, input, input, input, input)));

		Todo todo = todoService.getById(1L);

		assertAll(
				() -> assertThat(todo.getId()).isEqualTo(1),
				() -> assertThat(todo.getTitle()).isEqualTo(input),
				() -> assertThat(todo.getContents()).isEqualTo(input),
				() -> assertThat(todo.getUser()).isEqualTo(input),
				() -> assertThat(todo.getStatus()).isEqualTo(input)
		);
	}

	@ParameterizedTest
	@ValueSource(longs = {4444L, 5555L})
	void 특정_투두리스트_조회_실패(Long id) {
		given(todoRepository.findById(id))
			.willThrow(new NoSuchTodoFoundException("조회할 수 없는 Todo 입니다. id : " + id));

		assertThatThrownBy(() -> todoService.getById(id))
			.isInstanceOf(NoSuchTodoFoundException.class);
	}
}
