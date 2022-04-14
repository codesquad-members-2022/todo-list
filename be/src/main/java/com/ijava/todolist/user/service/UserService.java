package com.ijava.todolist.user.service;

import com.ijava.todolist.user.domain.User;
import com.ijava.todolist.user.exception.UserNotFoundException;
import com.ijava.todolist.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Long DEFAULT_USER_ID = 1L;

    private final UserRepository userRepository;

    private User findUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(UserNotFoundException::new);
    }

    public Long findDefaultUserId() {
        User defaultUser = findUserById(DEFAULT_USER_ID);
        return defaultUser.getId();
    }
}
