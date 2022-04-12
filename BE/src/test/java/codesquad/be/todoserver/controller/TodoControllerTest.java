package codesquad.be.todoserver.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.exception.NoSuchTodoFoundException;
import codesquad.be.todoserver.service.TodoService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(TodoController.class)
@DisplayName("API /api/todos/* 컨트롤러 계층 단위 테스트")
class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TodoService todoService;

	@Test
	void 특정_투두리스트_조회_성공() throws Exception {
		given(todoService.getById(1L))
			.willReturn(new Todo(1L, "Github 공부하기", "add, commit, push", "sam", "todo"));

		ResultActions perform = mockMvc.perform(get("/api/todos/1"));

		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void 특정_투두리스트_조회_실패() throws Exception {
		given(todoService.getById(4444L))
			.willThrow(new NoSuchTodoFoundException("id: " + 4444));

		ResultActions perform = mockMvc.perform(get("/api/todos/4444"));

		perform
			.andExpect(status().isNotFound());
	}

	@Test
	void 전체_투두_조회_성공() throws Exception {
		List<Todo> todos = createTestData();

		given(todoService.findTodos())
			.willReturn(todos);

		ResultActions perform = mockMvc.perform(get("/api/todos"));

		perform
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	private List<Todo> createTestData() {
		List<Todo> todos = new ArrayList<>();
		todos.add(new Todo(1L, "Github 공부하기", "add, commit, push", "sam", "todo"));
		todos.add(new Todo(2L, "블로그에 포스팅할 것", "*Github 공부내용 \n" +
				" *모던 자바스크립트 1장 공부내용", "sam", "todo"));
		return todos;
	}
}
