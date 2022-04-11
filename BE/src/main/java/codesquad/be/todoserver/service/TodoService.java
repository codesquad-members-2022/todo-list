package codesquad.be.todoserver.service;

import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.exception.NoSuchTodoFoundException;
import codesquad.be.todoserver.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public Optional<List> findTodos() {
		return todoRepository.findAll();
	}
}
