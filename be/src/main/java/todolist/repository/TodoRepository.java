package todolist.repository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository<T> {
    T save(T entity);

    Optional<T> findById(Long id);

    List<T> findAll();

    void delete(Long id);
}
