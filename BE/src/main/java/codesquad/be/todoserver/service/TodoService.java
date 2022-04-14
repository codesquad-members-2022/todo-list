package codesquad.be.todoserver.service;

import codesquad.be.todoserver.controller.TodoDtoMapper;
import codesquad.be.todoserver.controller.model.RegisterTodoDto;
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
		List<Todo> todos = todoRepository.findAllTodos();

		if (todos.isEmpty()) {
			throw new NoSuchElementException("Empty Todos");
		}
		return todos;
	}

	public Todo registerTodo(RegisterTodoDto registerTodoDto) {
		Todo saveTodo = todoRepository.saveTodo(
			TodoDtoMapper.toDomainFromRegisterTodoDto(registerTodoDto));

		History history = History.createAddHistoryBy(saveTodo);
		historyRepository.saveHistory(history);
		return saveTodo;
	}

	public boolean deleteById(Long id) {
		todoRepository.findById(id)
			.orElseThrow(() -> new NoSuchTodoFoundException("id: " + id));
		return todoRepository.deleteById(id);
	}
}
