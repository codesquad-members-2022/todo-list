package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository {

	Optional<Todo> findById(Long id);

	Optional<List> findAllTodos();
}
