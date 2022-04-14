package com.todolist.project.service;

import com.todolist.project.domain.log.LogRepository;
import com.todolist.project.web.dto.LogListDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
