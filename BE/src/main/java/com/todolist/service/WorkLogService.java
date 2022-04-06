package com.todolist.service;

import com.todolist.domain.WorkLog;
import com.todolist.dto.WorkLogListDto;
import com.todolist.repository.WorkLogRepository;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WorkLogService {

    private final WorkLogRepository workLogRepository;

    public WorkLogService(WorkLogRepository workLogRepository) {
        this.workLogRepository = workLogRepository;
    }

    public WorkLogListDto getWorkLogList() {
        return new WorkLogListDto(workLogRepository.findAllWorkLogs()
            .stream().map(WorkLog::convertToDto).collect(Collectors.toList()));
    }
}
