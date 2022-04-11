package com.example.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.domain.repository.LogJdbcTemplateRepository;
import com.example.backend.web.dto.LogListResponseDto;
import com.example.backend.web.dto.LogSaveRequestDto;

@Service
public class LogService {

	private final LogJdbcTemplateRepository logJdbcTemplateRepository;

	public LogService(LogJdbcTemplateRepository logJdbcTemplateRepository) {
		this.logJdbcTemplateRepository = logJdbcTemplateRepository;
	}

	public List<LogListResponseDto> findAll() {
		return logJdbcTemplateRepository.findAll();
	}

	public Long save(LogSaveRequestDto dto) {
		return logJdbcTemplateRepository.save(dto.toEntity());
	}
}
