package com.hooria.todo.service;

import com.hooria.todo.domain.ActivityLog;
import com.hooria.todo.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public List<ActivityLog> selectAll() {
        return activityLogRepository.findAll();
    }

    public int removeById(@PathVariable long id) {
        return activityLogRepository.deleteById(id);
    }
}
