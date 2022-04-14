package codesquad.todo.domain.user;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryUserRepository implements UserRepository {

    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong();

    public MemoryUserRepository() {
        init();
    }

    private void init() {
        User defaultUser = new User("testUser");
        save(defaultUser);
    }

    public Long save(User user) {
        Long userId = sequence.incrementAndGet();
        user.initId(userId);
        store.put(userId, user);
        return userId;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }
}
