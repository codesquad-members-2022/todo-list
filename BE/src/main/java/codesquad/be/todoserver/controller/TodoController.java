package codesquad.be.todoserver.controller;

import codesquad.be.todoserver.controller.model.RegisterTodoDto;
import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.service.TodoService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@PostMapping("/todos")
	@ResponseStatus(HttpStatus.CREATED)
	public Todo registerTodo(@Valid @RequestBody RegisterTodoDto registerTodoDto) {
		return todoService.registerTodo(registerTodoDto);
	}

	@DeleteMapping("/todos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTodo(@PathVariable Long id) {
		todoService.deleteById(id);
	}
}
