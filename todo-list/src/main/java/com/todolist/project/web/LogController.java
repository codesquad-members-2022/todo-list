package com.todolist.project.web;

import com.todolist.project.domain.log.Log;
import com.todolist.project.service.LogService;
import com.todolist.project.web.dto.LogListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/logs")
@RestController
public class LogController {
    private final LogService logService;

    @GetMapping
    public List<LogListDto> list(){
        return logService.findAll();
    }

    @PostMapping
    public int save(@RequestBody LogListDto dto) {
        return logService.saveLog(dto);
    }
}
