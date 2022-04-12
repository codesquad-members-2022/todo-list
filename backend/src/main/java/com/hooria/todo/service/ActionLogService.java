package com.hooria.todo.service;

import com.hooria.todo.domain.ActivityLog;
import com.hooria.todo.repository.ActionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActionLogService {

    private final ActionLogRepository actionLogRepository;

    public List<ActivityLog> selectAll() {
        return actionLogRepository.findAll();
    }

    public ActivityLog add(ActivityLog activityLog) {
        return actionLogRepository.insert(activityLog);
    }

    public int removeById(@PathVariable long id) {
        return actionLogRepository.deleteById(id);
    }
}
