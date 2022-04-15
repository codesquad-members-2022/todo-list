package kr.codesquad.todolist.service;

import kr.codesquad.todolist.dto.log.LogRequest;
import kr.codesquad.todolist.dto.log.LogResponse;
import kr.codesquad.todolist.repository.log.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public boolean save(LogRequest logRequest) {
        return logRepository.save(logRequest.toEntity());
    }

    public List<LogResponse> findAll() {
        return logRepository.findAll().stream().map(LogResponse::from).collect(Collectors.toList());
    }


}
