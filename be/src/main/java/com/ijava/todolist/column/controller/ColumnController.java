package com.ijava.todolist.column.controller;

import com.ijava.todolist.card.controller.dto.CardResponse;
import com.ijava.todolist.card.service.CardService;
import com.ijava.todolist.column.controller.dto.ColumnResponse;
import com.ijava.todolist.column.domain.Column;
import com.ijava.todolist.column.service.ColumnService;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "columns", description = "컬럼 API")
@RequiredArgsConstructor
@RestController
public class ColumnController {

    private final ColumnService columnService;
    private final CardService cardService;

    @Operation(summary = "컬럼 목록 조회",
            description = "컬럼 목록을 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "컬럼 조회 성공",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ColumnResponse.class))
                                    )
                            }
                    )
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/columns")
    public List<ColumnResponse> columnList() {
        List<Column> columnList = columnService.findColumns();
        return columnList.stream()
            .map(column -> ColumnResponse.from(column, cardService))
            .collect(Collectors.toList());
    }
}
