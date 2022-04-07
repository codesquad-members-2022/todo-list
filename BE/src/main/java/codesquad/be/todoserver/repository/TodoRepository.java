package codesquad.be.todoserver.repository;

import codesquad.be.todoserver.domain.Todo;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository {

	Optional<Todo> findById(Long id);
}
