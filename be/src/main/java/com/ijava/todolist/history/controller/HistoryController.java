package com.ijava.todolist.history.controller;

import com.ijava.todolist.card.controller.dto.CardResponse;
import com.ijava.todolist.history.Action;
import com.ijava.todolist.history.controller.dto.HistoryResponse;
import com.ijava.todolist.history.domain.History;
import com.ijava.todolist.history.repository.dto.JoinedHistory;
import com.ijava.todolist.history.service.HistoryService;
import com.ijava.todolist.history.util.HistoryResolver;
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

@Tag(name = "history", description = "활동 기록 API")
@RestController
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @Operation(summary = "활동 기록 조회",
            description = "활동 기록 목록을 조회합니다",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "활동 기록 조회 성공",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = HistoryResponse.class))
                                    )
                            }
                    )
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/history")
    public List<HistoryResponse> historyList() {
        return historyService.findAllJoinedHistory()
                .stream()
                .map(HistoryResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }
}
