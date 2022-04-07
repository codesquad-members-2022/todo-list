package com.example.backend.domain.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.backend.domain.Log;

public class LogRepository {
	private final List<Log> logList = new ArrayList<>();

	public void save(Log log) {
		logList.add(log);
	}

	public List<Log> findAllLog() {
		return Collections.unmodifiableList(logList);
	}
}
