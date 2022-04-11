package com.ijava.todolist.user.repository;

import com.ijava.todolist.user.domain.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);
}
