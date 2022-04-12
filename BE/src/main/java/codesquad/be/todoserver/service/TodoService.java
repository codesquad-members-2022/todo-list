package codesquad.be.todoserver.service;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.exception.NoSuchTodoFoundException;
import codesquad.be.todoserver.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

		if (todoRepository.findAllTodos().isEmpty()) {
			throw new NoSuchElementException();
		}
		return todoRepository.findAllTodos();
	}
}
