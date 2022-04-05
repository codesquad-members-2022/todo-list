package todolist.repository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository<T> {
    T save();
    Optional<T> findById();
    List<T> findAll();
    void delete();
}
