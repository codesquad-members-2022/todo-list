package com.ijava.todolist.card.controller;

import com.ijava.todolist.card.controller.dto.*;
import com.ijava.todolist.card.service.CardActionService;
import com.ijava.todolist.card.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "cards", description = "카드 API")
@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final CardActionService cardActionService;

    @Operation(summary = "카드 목록 조회",
            description = "특정 컬럼에 속한 카드 목록을 조회합니다",
            responses = {
                @ApiResponse(responseCode = "200",
                        description = "게시글 조회 성공",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CardResponse.class))
                            )
                        }
                )
            }
    )
    @GetMapping("/cards")
    public List<CardResponse> cardList(@RequestParam(value="columnId") Long columnId) {
        return cardService.findCardList(columnId)
                .stream()
                .map(CardResponse::from)
                .sorted(Comparator.comparing(CardResponse::getCreatedDate))
                .collect(Collectors.toUnmodifiableList());
    }

    @Operation(summary = "카드 입력",
            description = "새로운 카드를 추가합니다",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "카드 입력 성공",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CardResponse.class)
                                    )
                            }
                    )
            }
    )
    @PostMapping("/cards")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CardResponse createCard(@RequestBody CardCreateRequest cardCreateRequest) {
        return cardActionService.add(cardCreateRequest);
    }

    @Operation(summary = "카드 수정",
            description = "카드를 수정합니다",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "카드 수정 성공"
                    )
            }
    )
    @PutMapping("/cards/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateCard(@PathVariable("id") Long id, @RequestBody CardUpdateRequest updateRequest) {
        cardActionService.update(id, updateRequest);
    }

    @Operation(summary = "카드 칼럼 이동",
            description = "카드의 컬럼을 변경합니다",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "카드 칼럼 이동 성공",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CardMovedResponse.class)
                                    )
                            }
                    )
            }
    )
    @PatchMapping("/cards")
    public CardMovedResponse moveCard(@RequestBody CardMoveRequest cardMoveRequest) {
        return cardActionService.move(cardMoveRequest);
    }

    @Operation(summary = "카드 삭제",
            description = "카드를 삭제합니다",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "카드 삭제 성공",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CardDeleteResponse.class)
                                    )
                            }
                    )
            }
    )
    @DeleteMapping("/cards/{id}")
    public CardDeleteResponse deleteCard(@PathVariable("id") Long id) {
        return cardActionService.delete(id);
    }
}
