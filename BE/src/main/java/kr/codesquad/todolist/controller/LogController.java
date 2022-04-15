package kr.codesquad.todolist.controller;

import kr.codesquad.todolist.dto.log.LogResponse;
import kr.codesquad.todolist.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LogController {

    private final Logger log = LoggerFactory.getLogger(LogController.class);
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/log")
    public ResponseEntity<List<LogResponse>> findLogs() {
        List<LogResponse> logs = logService.findAll();

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

}
