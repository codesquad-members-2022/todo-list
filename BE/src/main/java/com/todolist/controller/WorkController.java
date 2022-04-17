package com.todolist.controller;

import com.todolist.dto.ColumnListDto;
import com.todolist.dto.ModifiedWorkDto;
import com.todolist.dto.WorkDeletionDto;
import com.todolist.dto.WorkDto;
import com.todolist.dto.WorkCreationDto;
import com.todolist.dto.WorkModificationDto;
import com.todolist.dto.WorkMovementDto;
import com.todolist.service.WorkService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("works")
public class WorkController {

    private final WorkService workService;

    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping
    public ColumnListDto getColumnList(@RequestParam("userId") String userId) {
        return workService.getColumnList(userId);
    }

    @PostMapping
    public WorkDto create(@RequestBody WorkCreationDto workCreationDto) {
        return workService.create(workCreationDto);
    }

    @PatchMapping
    public void move(@RequestBody WorkMovementDto workMovementDto) {
        workService.move(workMovementDto);
    }

    @PostMapping("{workId}")
    public void remove(@PathVariable Integer workId, @RequestBody WorkDeletionDto workDeletionDto) {
        workService.remove(workId, workDeletionDto);
    }

    @PatchMapping("{workId}")
    public ModifiedWorkDto modify(@PathVariable Integer workId, @RequestBody WorkModificationDto workModificationDto) {
        return workService.modify(workId, workModificationDto);
    }
}
