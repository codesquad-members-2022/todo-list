package todolist.repository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository<T> {
    T save(T entity);

    T findById(Long id);

    List<T> findAll();

    void update(T t);

    void delete(Long id);
}
