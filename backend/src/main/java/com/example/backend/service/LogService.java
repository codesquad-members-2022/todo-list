package com.example.backend.service;

import com.example.backend.domain.repository.LogJdbcTemplateRepository;
import com.example.backend.web.dto.LogListResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogJdbcTemplateRepository logJdbcTemplateRepository;

    public LogService(LogJdbcTemplateRepository logJdbcTemplateRepository) {
        this.logJdbcTemplateRepository = logJdbcTemplateRepository;
    }

    public List<LogListResponseDto> findAll() {
        return logJdbcTemplateRepository.findAll();
    }
}
