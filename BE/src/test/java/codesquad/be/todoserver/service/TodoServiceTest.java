package codesquad.be.todoserver.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.exception.NoSuchTodoFoundException;
import codesquad.be.todoserver.repository.HistoryRepository;
import codesquad.be.todoserver.repository.TodoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("API /api/todos/* 서비스 계층 단위 테스트")
class TodoServiceTest {

	private TodoRepository todoRepository = mock(TodoRepository.class);
	private HistoryRepository historyRepository  = mock(HistoryRepository.class);
	private TodoService todoService = new TodoService(todoRepository, historyRepository);

	@Test
	void 특정_투두리스트_조회_성공() {
		given(todoRepository.findById(1L))
			.willReturn(
				Optional.of(new Todo(1L,"Github 공부하기", "add, commit, push", "sam", "todo")));

		Todo todo = todoService.getById(1L);

		assertAll(
			() -> assertThat(todo.getId()).isEqualTo(1),
			() -> assertThat(todo.getTitle()).isEqualTo("Github 공부하기"),
			() -> assertThat(todo.getContents()).isEqualTo("add, commit, push"),
			() -> assertThat(todo.getUser()).isEqualTo("sam"),
			() -> assertThat(todo.getStatus()).isEqualTo("todo")
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

	@Test
	void 전체_투두리스트_조회_성공() {
		List<Todo> todolist = createTestData();

		given(todoRepository.findAll())
			.willReturn(todolist);

		List<Todo> todos = todoService.findTodos();

		assertThat(todos).contains(todolist.get(0));
		assertThat(todos).contains(todolist.get(1));
	}

	@Test
	void 특정_투두_삭제_성공() {
		given(todoRepository.findById(2L))
			.willReturn(Optional.of(new Todo(2L, "Github 공부하기", "add, commit, push", "sam", "todo")));
		given(todoRepository.deleteById(2L))
			.willReturn(true);

		boolean result = todoService.deleteById(2L);

		assertThat(result).isTrue();
	}

	private List<Todo> createTestData() {
		List<Todo> todolist = new ArrayList<>();
		todolist.add(new Todo(1L, "Github 공부하기", "add, commit, push", "sam", "todo"));
		todolist.add(new Todo(2L, "블로그에 포스팅할 것", "*Github 공부내용 \n" + " *모던 자바스크립트 1장 공부내용", "sam", "todo"));
		return todolist;
	}
}
