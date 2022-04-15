package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.Todo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository {

	Optional<Todo> findById(Long id);

	List<Todo> findAll();

	Todo save(Todo todo);

	boolean deleteById(Long id);
}
