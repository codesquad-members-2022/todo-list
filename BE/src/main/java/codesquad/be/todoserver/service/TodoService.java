package codesquad.be.todoserver.service;

import codesquad.be.todoserver.controller.TodoDtoMapper;
import codesquad.be.todoserver.controller.model.RegisterTodoDto;
import codesquad.be.todoserver.domain.Action;
import codesquad.be.todoserver.domain.History;
import codesquad.be.todoserver.domain.Todo;
import codesquad.be.todoserver.exception.NoSuchTodoFoundException;
import codesquad.be.todoserver.repository.HistoryRepository;
import codesquad.be.todoserver.repository.TodoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

	private final TodoRepository todoRepository;
	private final HistoryRepository historyRepository;

	public TodoService(TodoRepository todoRepository,
		HistoryRepository historyRepository) {
		this.todoRepository = todoRepository;
		this.historyRepository = historyRepository;
	}

	public Todo getById(Long id) {
		return todoRepository.findById(id)
			.orElseThrow(() -> new NoSuchTodoFoundException("id: " + id));
	}

	public List<Todo> findTodos() {
		List<Todo> todos = todoRepository.findAll();

		if (todos.isEmpty()) {
			throw new NoSuchElementException("Empty Todos");
		}
		return todos;
	}

	public Todo registerTodo(RegisterTodoDto registerTodoDto) {
		Todo todo = todoRepository.save(
			TodoDtoMapper.toDomainFromRegisterTodoDto(registerTodoDto));

		historyRepository.save(History.of(todo, Action.ADD));
		return todo;
	}

	public boolean deleteById(Long id) {
		Todo todo = todoRepository.findById(id)
			.orElseThrow(() -> new NoSuchTodoFoundException("id: " + id));

		historyRepository.save(History.of(todo, Action.REMOVE));
		return todoRepository.deleteById(id);
	}
}
