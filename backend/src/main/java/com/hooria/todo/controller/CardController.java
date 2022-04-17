package com.hooria.todo.controller;

import com.hooria.todo.dto.AddCardRequest;
import com.hooria.todo.dto.CardResponse;
import com.hooria.todo.dto.UpdateCardRequest;
import com.hooria.todo.dto.UpdateCardLayoutRequest;
import com.hooria.todo.error.CardRunTimeException;
import com.hooria.todo.error.ErrorCode;
import com.hooria.todo.error.ErrorResponse;
import com.hooria.todo.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Card(타스크) Controller")
@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @ApiOperation(
            value = "새로운 타스크 등록",
            notes = "새로운 타스크를 등록한다.",
            produces = "application/json",
            response = CardResponse.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "addCardRequest",
                    value = "새로운 타스크"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "등록 성공"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PostMapping
    public CardResponse addCard(@RequestBody AddCardRequest addCardRequest) {
        return cardService.add(addCardRequest);
    }

    @ApiOperation(
            value = "모든 타스크 목록 조회",
            notes = "모든 타스크 목록을 조회한다.",
            produces = "application/json",
            response = List.class
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @GetMapping
    public List<CardResponse> getCards() {
        return cardService.selectAll();
    }

    @ApiOperation(
            value = "타스크 수정",
            notes = "타스크를 수정한다.",
            produces = "application/json",
            response = CardResponse.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "updateCardRequest",
                    value = "새로운 할 일"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 성공"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PatchMapping("/{id}")
    public CardResponse updateCard(@RequestBody UpdateCardRequest updateCardRequest) {
        return cardService.update(updateCardRequest);
    }

    @ApiOperation(
            value = "해당 'id'를 가진 타스크 삭제",
            notes = "해당 'id'를 가진 타스크를 삭제한다.",
            produces = "application/json",
            response = CardResponse.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "타스크 아이디"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 성공"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @DeleteMapping("/{id}")
    public CardResponse delete(@PathVariable long id) {
        return cardService.delete(id);
    }

    @ApiOperation(
            value = "타스크 정렬 순서 일괄 저장",
            notes = "타스크 정렬 순서를 일괄 저장한다.",
            produces = "application/json",
            response = List.class
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "updateCardLayoutRequests",
                    value = "새로운 할 일"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 성공"),
            @ApiResponse(code = 500, message = "서버 에러"),
    })
    @PatchMapping("/layout")
    public List<CardResponse> updateCardsLayout(@RequestBody List<UpdateCardLayoutRequest> updateCardLayoutRequests) {
        return cardService.updateCardsLayout(updateCardLayoutRequests);
    }

    @ExceptionHandler(CardRunTimeException.class)
    private ResponseEntity<ErrorResponse> handleCardRuntimeException(CardRunTimeException e) {
        ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(ErrorResponse.of(errorCode), errorCode.getStatus());
    }
}
