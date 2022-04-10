package com.example.backend.controller.column;

import com.example.backend.domain.column.Column;
import com.example.backend.repository.column.ColumnRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/columns")
public class ColumnController {

    private final ColumnRepository columnRepository;

    public ColumnController(ColumnRepository columnRepository) {
        this.columnRepository = columnRepository;
    }

    @GetMapping("")
    public List<Column> getColumns(){
        return columnRepository.findAll();
    }

}
