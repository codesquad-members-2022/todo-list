package codesquad.be.todoserver.controller;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TodoController {

	@Autowired
	private TodoService todoService;

	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@GetMapping("/todos/{id}")
	public Todo getById(@PathVariable Long id) {
		return todoService.getById(id);
	}
}
