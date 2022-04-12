package com.todolist.project.service;

import com.todolist.project.domain.log.LogRepository;
import com.todolist.project.web.dto.LogListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LogService {

    private final LogRepository logRepository;

    public List<LogListDto> findAll() {
        return logRepository.findAll();
    }


    public int saveLog(LogListDto dto) {
        return logRepository.save(dto.toEntity());
    }
}
