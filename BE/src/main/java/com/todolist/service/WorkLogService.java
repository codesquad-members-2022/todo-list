package com.todolist.service;

import com.todolist.domain.WorkLog;
import com.todolist.dto.WorkLogDto;
import com.todolist.dto.WorkLogListDto;
import com.todolist.repository.WorkLogRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WorkLogService {

    private final WorkLogRepository workLogRepository;

    public WorkLogService(WorkLogRepository workLogRepository) {
        this.workLogRepository = workLogRepository;
    }

    public WorkLogListDto getWorkLogList(String userId) {
        List<WorkLogDto> workLogDtoList = workLogRepository.findAllByUserId(userId)
            .stream().map(WorkLog::convertToDto).collect(Collectors.toList());

        return WorkLogListDto.builder().userId(userId).workLogList(workLogDtoList).build();
    }
}
