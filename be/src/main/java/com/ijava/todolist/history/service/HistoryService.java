package com.ijava.todolist.history.service;

import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.exception.HistoryNotSavedException;
import com.ijava.todolist.history.repository.HistoryRepository;
import com.ijava.todolist.user.service.UserService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final UserService userService;

    public void store(Long cardId, Long columnsId, Action action, LocalDateTime createdDate) {
        try {
            Long userId = getDefaultUserId();
            History history = new History(userId, cardId, columnsId, action, createdDate);
            historyRepository.save(history);
        } catch (NullPointerException e) {
            throw new HistoryNotSavedException();
        }
    }

    public List<History> findHistories() {
        return historyRepository.findAll()
            .orElseGet(Collections::emptyList);
    }

    private Long getDefaultUserId() {
        return userService.findDefaultUserId();
    }
}
