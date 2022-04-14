package codesquad.todo.domain.user;

import java.util.Optional;

public interface UserRepository {

    Long save(User user);
    Optional<User> findById(Long id);
}
