package com.example.backend.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.LogService;
import com.example.backend.web.dto.LogListResponseDto;

import io.swagger.annotations.ApiOperation;

@RestController
public class LogsController {

	private final LogService logService;

	public LogsController(LogService logService) {
		this.logService = logService;
	}

	@ApiOperation(value = "Log 조회")
	@GetMapping("/logs")
	public List<LogListResponseDto> logList() {
		return logService.findAll();
	}
}
