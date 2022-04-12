package codesquad.be.todoserver.controller;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.service.TodoService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	private final TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/todos/{id}")
	public Todo getById(@PathVariable Long id) {
		return todoService.getById(id);
	}

	@GetMapping("/todos")
	public List<Todo> todoList() {
		return todoService.findTodos();
	}
}
