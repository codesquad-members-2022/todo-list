package codesquad.be.todoserver.service;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.exception.NoSuchTodoFoundException;
import codesquad.be.todoserver.repository.TodoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public Todo getById(Long id) {
		return todoRepository.findById(id)
			.orElseThrow(() -> new NoSuchTodoFoundException("id: " + id));
	}

	public List<Todo> findTodos() {
		List<Todo> todos = todoRepository.findAllTodos();

		if (todos.isEmpty()) {
			throw new NoSuchElementException("Empty Todos");
		}
		return todos;
	}
}
