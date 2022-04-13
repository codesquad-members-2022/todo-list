package com.codesquad.todolist.column;

import com.codesquad.todolist.column.dto.ColumnResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Column API")
@RestController
@RequestMapping("/columns")
public class ColumnController {

    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @ApiOperation(value = "컬럼 및 카드 조회", notes = "컬럼 별로 포함된 모든 카드를 조회합니다.")
    @GetMapping
    public List<ColumnResponse> findColumns() {
        return columnService.findAll();
    }
}
