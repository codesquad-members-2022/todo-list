package com.codesquad.todolist.card;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codesquad.todolist.card.dto.CardCreateRequest;
import com.codesquad.todolist.card.dto.CardMoveRequest;
import com.codesquad.todolist.card.dto.CardUpdateRequest;
import com.codesquad.todolist.history.dto.HistoryResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Card API")
@RestController
@RequestMapping("/cards")
@Validated
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @ApiOperation(value = "카드 추가", notes = "새로운 카드를 지정한 컬럼에 추가합니다.")
    @PostMapping
    public ResponseEntity<HistoryResponse> createCard(
        @RequestBody @Valid CardCreateRequest request) {
        HistoryResponse historyResponse = cardService.create(request);
        return new ResponseEntity<>(historyResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "카드 업데이트", notes = "지정한 카드를 새로운 데이터로 업데이트합니다.")
    @PatchMapping("/{id}")
    public HistoryResponse updateCard(
        @ApiParam(name = "id", value = "업데이트할 카드 Id", required = true)
        @PathVariable(value = "id") Integer cardId,
        @RequestBody @Valid CardUpdateRequest request) {
        return cardService.update(cardId, request);
    }

    @ApiOperation(value = "카드 이동", notes = "카드를 현재 컬럼 혹은 다른 컬럼으로 이동하고 순서를 변경합니다.")
    @PutMapping("/{id}/move")
    public ResponseEntity<HistoryResponse> moveCard(
        @ApiParam(name = "id", value = "이동할 카드 Id", required = true)
        @PathVariable(value = "id") Integer cardId,
        @RequestBody @Valid CardMoveRequest request) {
        HistoryResponse historyResponse = cardService.move(cardId, request);
        return new ResponseEntity<>(historyResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "카드 삭제", notes = "지정한 카드를 삭제합니다.")
    @DeleteMapping("/{id}")
    public HistoryResponse deleteCard(
        @ApiParam(name = "id", value = "삭제할 카드 Id", required = true)
        @PathVariable(value = "id") Integer cardId) {
        return cardService.delete(cardId);
    }

}
